package com.miviclin.tuentichallenge4.challenge04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DnaMutator {

	private static final int MAX_MUTATIONS_PER_TRANSITION = 1;

	private DnaState sourceState;
	private DnaState targetState;
	private Set<DnaState> permittedSafeStates;

	public DnaMutator(DnaState sourceState, DnaState targetState, Set<DnaState> permittedSafeStates) {
		this.sourceState = sourceState;
		this.targetState = targetState;
		this.permittedSafeStates = permittedSafeStates;
	}

	public DnaTransitionList calculateMinimumTransitions() {
		if (sourceState.equals(targetState)) {
			DnaTransitionList minimumTransitions = new DnaTransitionList();
			minimumTransitions.add(sourceState);
			return minimumTransitions;
		}

		Node<DnaState> rootNode = new Node<DnaState>(sourceState);
		Node<DnaState> targetNode = mutateIntoTargetState(rootNode);
		DnaTransitionList minimumTransitions = new DnaTransitionList();
		minimumTransitions.add(targetState);

		Node<DnaState> parent = null;
		Node<DnaState> currentNode = targetNode;
		while ((parent = currentNode.getParent()) != null) {
			minimumTransitions.add(parent.getValue());
			currentNode = parent;
		}

		Collections.reverse(minimumTransitions);

		return minimumTransitions;
	}

	private Node<DnaState> mutateIntoTargetState(Node<DnaState> node) {
		if (node == null) {
			return null;
		}

		if (node.getValue().equals(targetState)) {
			return node;
		}

		List<DnaState> statesToIgnoreForNode = getStatesToIgnoreForNode(node);
		List<DnaState> possibleMutationsForState = getPossibleMutationsForState(node.getValue(), statesToIgnoreForNode);

		if (possibleMutationsForState.isEmpty()) {
			return null;
		}

		for (DnaState dnaState : possibleMutationsForState) {
			Node<DnaState> childNode = new Node<DnaState>(node, dnaState);
			Node<DnaState> targetNode = mutateIntoTargetState(childNode);
			if (targetNode != null) {
				return targetNode;
			}
		}
		return null;
	}

	private List<DnaState> getPossibleMutationsForState(DnaState state, List<DnaState> statesToIgnore) {
		List<DnaState> safeMutations = new ArrayList<>();

		for (DnaState permittedSafeState : permittedSafeStates) {
			if (permittedSafeState.equals(sourceState)) {
				continue;
			}

			if (statesToIgnore.contains(permittedSafeState)) {
				continue;
			}

			int numNonMatchingNucleotides = state.countNonMatchingNucleotides(permittedSafeState);
			if ((numNonMatchingNucleotides > 0) && (numNonMatchingNucleotides <= MAX_MUTATIONS_PER_TRANSITION)) {
				safeMutations.add(permittedSafeState);
			}
		}

		return safeMutations;
	}

	private List<DnaState> getStatesToIgnoreForNode(Node<DnaState> node) {
		List<DnaState> statesToIgnore = new ArrayList<>();
		statesToIgnore.add(node.getValue());

		Node<DnaState> parent = null;
		Node<DnaState> currentNode = node;
		while ((parent = currentNode.getParent()) != null) {
			statesToIgnore.add(parent.getValue());
			currentNode = parent;
		}

		return statesToIgnore;
	}

}
