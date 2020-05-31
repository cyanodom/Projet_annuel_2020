package steinerGraphJava.algorithms.dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import steinerGraphJava.graph.IGraph;
import steinerGraphJava.graphics.PointTime;

public final class Dijkstra {
	private static final int INFINITY = -1;
	private static final int LESS = -1;
	private static final int MORE = 1;
	private static final int EQUAL = 0;
	
	private Dijkstra() {
	}
	
	public static String[] dijkstra(IGraph g, String start, String end, 
			boolean onlyArc) {
		//INITIALIZE
		String current = start;
		Map<String, Integer> nodes = nodes(g);
		boolean[] open = new boolean[nodes.size()];
		fillTrue(open);
		String[] goFrom = new String[nodes.size()];
		int[] distances = new int[nodes.size()];
		fillInfinity(distances);
		distances[indexOf(nodes, start)] = 0;
		//CODE
		while (current != end) {
			close(nodes, open, current);
			for (PointTime canGo : canGoOpenNodesFrom(g,
					nodes, open, current)) {
				int actualTime = distances[
				    indexOf(nodes, canGo.getPoint())];
				int potTime = distances[
				    indexOf(nodes, current)] 
				    + (onlyArc ? 1 : canGo.getTime());
				if (compare(potTime, actualTime) == LESS) {
					goFrom[indexOf(
					    nodes, canGo.getPoint())
					    ] = current;
					distances[indexOf(
						    nodes, canGo.getPoint())
							    ] = potTime;
				}
			}
			current = getMinOpenDistance(nodes, open, distances);
		}		
		return getPathFrom(goFrom, start, end, nodes);
	}
	
	// OUTILS
	
	private static String getMinOpenDistance(
			Map<String, Integer> nodes, boolean[] open,
			int[] distances) {
		String min = "";
		boolean firstTime = true;
		for (String c : nodes.keySet()) {
			if (!isOpen(nodes, open, c)) {
				continue;
			}
			if (firstTime) {
				firstTime = false;
				min = c;
				continue;
			}
			if (compare(distances[indexOf(nodes, c)],
					distances[indexOf(nodes, min)]) == LESS) {
				min = c;
			}
		}
		return min;
	}
	
	private static PointTime[] canGoOpenNodesFrom(IGraph g, 
			Map<String, Integer> nodes, boolean[] open, String c) {
		List<PointTime> openNodes = new ArrayList<>();
		for (PointTime pt : g.getListSucc().get(c)) {
			if (isOpen(nodes, open, pt.getPoint())) {
				openNodes.add(pt);
			}
		}
		return toArray(openNodes);
	}
	
	private static void close(Map<String, Integer> nodes, boolean[] open,
			String c) {
		open[indexOf(nodes, c)] = false;
	}
	
	private static boolean isOpen(Map<String, Integer> nodes,
			boolean[] open, String c) {
		return open[indexOf(nodes, c)];
	}
	
	private static int indexOf(Map<String, Integer> nodes, String c) {
		return nodes.get(c);
	}
	
	private static Map<String, Integer> nodes(IGraph g) {
		Map<String, Integer> rt = new HashMap<>();
		int i = 0;
		for (String c : g.getListSucc().keySet()) {
			rt.put(c, i);
			++i;
		}
		return rt;
	}
	
	private static PointTime[] toArray(List<PointTime> pts) {
		PointTime[] rt = new PointTime[pts.size()];
		int i = 0;
		for (PointTime pt : pts) {
			rt[i] = pt;
			++i;
		}
		return rt;
	}
	
	private static void fillInfinity(int[] d) {
		for (int i = 0; i < d.length; ++i) {
			d[i] = INFINITY;
		}
	}
	
	private static void fillTrue(boolean[]d) {
		for (int i = 0; i < d.length; ++i) {
			d[i] = true;
		}
	}
	
	private static String[] getPathFrom(String[] goFrom, String start, String end,
			Map<String, Integer> nodes) {
		List<String> prev = new ArrayList<>();
		prev.add(end);
		String current = goFrom[indexOf(nodes, end)];
		prev.add(current);
		while (current != start) {
			if (current == "") {
				return null;
			}
			current = goFrom[indexOf(nodes, current)];
			prev.add(current);
		}
		String[] ret = new String[prev.size()];
		int i = ret.length - 1;
		for (String c : prev) {
			ret[i] = c;
			--i;
		}
		return ret;
	}
	
	private static int compare(int a, int b) {
		if (a == INFINITY) {
			return MORE;
		}
		if (b == INFINITY) {
			return LESS;
		}
		if (a < b) {
			return LESS;
		}
		if (a > b) {
			return MORE;
		}
		return EQUAL;
	}
}
