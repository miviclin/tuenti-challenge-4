package com.miviclin.tuentichallenge4.challenge14;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 14 - Train Empire
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class Route {

	private Station startingStation;
	private Map<Station, Set<Station>> connections;
	private Set<Station> stations;

	public Route(Station startingStation) {
		this.startingStation = startingStation;
		this.connections = new HashMap<>();
		this.stations = new HashSet<>();
	}

	public Route(Route route) {
		this.startingStation = new Station(route.startingStation);
		this.connections = new HashMap<>();

		for (Entry<Station, Set<Station>> entry : route.connections.entrySet()) {
			Set<Station> connectedStations = new HashSet<>();
			for (Station station : entry.getValue()) {
				connectedStations.add(new Station(station));
			}
			this.connections.put(new Station(entry.getKey()), connectedStations);
		}
	}

	public void addConnection(Station station1, Station station2) {
		stations.add(station1);
		stations.add(station2);
		addConnectionFromSourceStation(station1, station2);
		addConnectionFromSourceStation(station2, station1);
	}

	private void addConnectionFromSourceStation(Station sourceStation, Station destinationStation) {
		Set<Station> connectionsFromSourceStation = connections.get(sourceStation);
		if (connectionsFromSourceStation == null) {
			connectionsFromSourceStation = new HashSet<>();
			connections.put(sourceStation, connectionsFromSourceStation);
		}
		connectionsFromSourceStation.add(destinationStation);
	}

	public Set<Station> getConnections(Station station) {
		return connections.get(station);
	}

	public Set<Station> getStations() {
		return stations;
	}

	public Station getStartingStation() {
		return startingStation;
	}

}
