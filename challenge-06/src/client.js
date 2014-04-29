#!/usr/bin/env node

if ((process.version.split('.')[1]|0) < 10) {
	console.log('Please, upgrade your node version to 0.10+');
	process.exit();
}

var net = require('net');
var util = require('util');
var crypto = require('crypto');

var options = {
	'port': 6969,
	'host': '54.83.207.90',
}

const KEYPHRASE = 'BlueBellPsychologicalFoxIsActive';

var dh, sdh, keyphrase, secret, state = 0;
	
var socket = net.connect(options, function() {
	socket.write('hello?');	
});
	
socket.on('data', function(data) {

	data = data.toString().trim().split('|');
		
	var outputInfo = 'CLIENT->SERVER:';
	var inputInfo = 'SERVER->CLIENT:';

	if (data[0].indexOf(outputInfo) > -1) {
		data[0] = data[0].substring(outputInfo.length, data[0].length);
			
		if (state == 0) {
			socket.write('hello!\n');
			state++;
			return;
		} else if (state == 1) {
			sdh = crypto.createDiffieHellman(dh.getPrime('hex'), 'hex');
			sdh.generateKeys();
			secret = sdh.computeSecret(dh.getPublicKey('hex'), 'hex');
			socket.write(util.format('key|%s\n', sdh.getPublicKey('hex')));
			state++;
		} else if (state == 2) {
			state++;
		}
	}

	if (data[0].indexOf(inputInfo) > -1) {
		data[0] = data[0].substring(inputInfo.length, data[0].length);
		
		if (state == 1 && data[0] == 'hello!') {
			dh = crypto.createDiffieHellman(256);
			dh.generateKeys();
			socket.write(util.format('key|%s|%s\n', dh.getPrime('hex'), dh.getPublicKey('hex')));
		} else if (state == 2 && data[0] == 'key') {
			secret = dh.computeSecret(data[1], 'hex');
			var cipher = crypto.createCipheriv('aes-256-ecb', secret, '');
			keyphrase = cipher.update(KEYPHRASE, 'utf8', 'hex') + cipher.final('hex');
			socket.write(util.format('keyphrase|%s\n', keyphrase));
		} else if (state == 3 && data[0] == 'result') {
			var decipher = crypto.createDecipheriv('aes-256-ecb', secret, '');
			var message = decipher.update(data[1], 'hex', 'utf8') + decipher.final('utf8');
			console.log(message);
			socket.end();
		} else {
			console.log('Error');
			socket.end();
		}
	}
});
