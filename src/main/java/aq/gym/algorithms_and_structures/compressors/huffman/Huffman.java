package aq.gym.algorithms_and_structures.compressors.huffman;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Huffman {
	
	private Node nodeTree;
	private Map<String, String> codes;
	
	public String decode(String encode) {
		if(nodeTree == null || codes == null)
			throw new IllegalStateException("Can't call until encode method won't call");
		StringBuilder decode = new StringBuilder();
		int index = -1;
		while(index < encode.length() - 1) {			
			index = decode0(nodeTree, index, encode, decode);
		}
		return decode.toString();
	}
	
	private int decode0(Node node, int index, String encode, StringBuilder decode) {
		if(node == null)
			return index;
		if(node.isLeaf()) {
			decode.append(node.getLetter());
			return index;
		}
		index++;
		node = (encode.charAt(index) == '0') ? node.getLeft() : node.getRight();
		index = decode0(node, index, encode, decode);
		return index;
	}
	
	public String encode(String text) {
		codes = new HashMap<String, String>();
		PriorityQueue<Node> nodes = mapToNodes(text);
		nodeTree = huffmanTree(nodes);
		codes = huffmanCodes(nodeTree, "");
		StringBuilder sb = new StringBuilder();
		Arrays.stream(text.split(""))
			.forEach(letter -> sb.append(codes.get(letter)));
		return sb.toString();
	}
	
	private PriorityQueue<Node> mapToNodes(String string) {
		Function<Map.Entry<String, Long>, Node> nodeMapper = entry -> {
			Node node = new Node();
			node.setLetter(entry.getKey());
			node.setFrequency(entry.getValue());
			return node;
		};
		return letterFrequencies(string).entrySet()
			.stream()
			.map(nodeMapper)
			.sorted()
			.collect(PriorityQueue::new, PriorityQueue::add, PriorityQueue::addAll);
	}
	
	private Map<String, Long> letterFrequencies(String string) {
		return Arrays.stream(string.split(""))
			.collect(Collectors.groupingBy(str -> str, Collectors.counting()));
	}
	
	private Node huffmanTree(PriorityQueue<Node> nodes) {
		while(nodes.size() != 1) {
			Node first = nodes.poll();
			Node second = nodes.poll();
			Node union = Node.compose(first, second);
			nodes.add(union);
		}
		nodeTree = nodes.peek(); 
		return nodeTree;
	}
	
	private Map<String, String> huffmanCodes(Node node, String path) {
		if(node == null) 
			return codes;
		if(node.isLeaf()) 
			this.codes.put(node.getLetter(), path);
		codes = huffmanCodes(node.getLeft(), path + "0");
		codes = huffmanCodes(node.getRight(), path + "1");
		return codes;
	}
	
}
