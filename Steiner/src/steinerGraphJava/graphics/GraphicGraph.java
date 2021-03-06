package steinerGraphJava.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.Scrollable;

import steinerGraphJava.graph.*;
import steinerGraphJava.model.ISteinerModel;
import steinerGraphJava.model.SteinerModel;

@SuppressWarnings({ "deprecation"})
public class GraphicGraph extends JComponent implements Scrollable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2792310977136639779L;

	// texte Liste des successeurs
	private static final String SUCC_LIST = "Liste des successeurs :";
	// Epaisseur des traits
	private static final int THICK = 1;
	// unitée de mesure de l'espacement
	private static final int SPACING = 10;
	// espacement des textes aux bordures horizontales
	private static final int H_SPACING_TEXT = 
			(int) ((1. / 2.) * SPACING);;
	// espacement des textes aux bordures verticale
	private static final int V_SPACING_TEXT = 
			(int) ((1. / 2.) * SPACING);
	// rayou des cercles des arcs
	private static final int ARC_SIZE = 16;
	// hauteure de la police d'écriture
	private static final int FONT_HEIGHT = 10;
	// hauteure de la police d'écriture des arcs
	private static final int ARC_FONT_HEIGHT = 10;
	//nombre maximum d' arc en liste
	private static final int N_ARC_LIST = 6;
	// taille de la flèche
	private static final int ARROW_LENGTH = 3 * SPACING;
	// hauteure préférée de l'élément
	private static final int PREFERED_HEIGHT = 
			8 * SPACING
			+ 5 * THICK
			+ 4 * ARC_SIZE
			+ 2 * FONT_HEIGHT
			+ 4 * V_SPACING_TEXT;
	// largeure préférée
	private static final int PREFERED_WIDTH = 
			5 * H_SPACING_TEXT
			+ 14 * SPACING
			+ 16 * ARC_SIZE;
	private static final Color BACKGROUND = new Color(255, 255, 255);
	private static final Color TEXT_COLOR = new Color(120, 120, 30);
	private static final Font FONT = new Font("Helvetica", Font.PLAIN, 12);
	
	private ISteinerModel model;
	
	private int realHeight;
	private int realWidth;
	
	public GraphicGraph(ISteinerModel model) {
		this.model = model;
		
		realHeight = PREFERED_HEIGHT;
		realWidth = PREFERED_WIDTH;
		
		this.setPreferredSize(new Dimension(
				realWidth, realHeight));
		
		
		model.addObserver(new Observer() {

			@Override
			public void update(Observable o, Object arg) {
				switch ((SteinerModel.State) arg) {
				case GRAPH_CHANGED:
					repaint();
				case NOT_SPECIFIED:
					repaint();
				default:
					break;
				}
			}
			
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setRenderingHint(
		        RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setFont(FONT);
		IGraph graph = model.getGraph();
		
		FontMetrics fm = g2.getFontMetrics(FONT);
		
		g2.setColor(BACKGROUND);
		g2.fillRect(0, 0, getWidth(), Integer.MAX_VALUE - 1);
		g2.setColor(TEXT_COLOR);
		
		//char[] rdvP = graphFile.getRdvPoints();
		int x = SPACING;
		int y = SPACING;
		
		// Dessins List succ Arcs
		x = SPACING;
		y += SPACING;
		drawTextEncadred(g2, fm, x, y, SUCC_LIST);
		y += 2 * (THICK + V_SPACING_TEXT)
				+ FONT_HEIGHT;
		
		x = SPACING;
		drawSuccArcList(g2, fm, x, y, graph);
		
		this.setPreferredSize(new Dimension(
				realWidth, realHeight));

		this.getParent().revalidate();
	}

	// OUTILS
	
	private void drawTextEncadred(Graphics g, FontMetrics fm, 
			int x, int y, String text) {

		int xSize = 2 * (H_SPACING_TEXT 
				+ THICK) + fm.stringWidth(text);
		
		int ySize = 2 * (THICK + V_SPACING_TEXT)
				+ FONT_HEIGHT;
		
		g.drawLine(x, y, x + xSize, y);
		g.drawLine(x + xSize, y, x + xSize, y + ySize);
		g.drawLine(x, y, x, y + ySize);
		
		y += FONT_HEIGHT + THICK + V_SPACING_TEXT;
		g.drawString(text, x + H_SPACING_TEXT + THICK, y);
	}
	
	private void drawArc(Graphics g, FontMetrics fm, int x, int y, String c) {
		g.drawOval(x, y, 2 * ARC_SIZE, 2 * ARC_SIZE);
		g.drawString(c, x + ARC_SIZE - fm.stringWidth(c) / 2,
				y + ARC_SIZE + ARC_FONT_HEIGHT / 2);
	}
	
	private void drawSuccArcList(Graphics g, FontMetrics fm, int x, int y,
			IGraph graph) {
		g.drawLine(x, y, x + SPACING * 7 + 6 * THICK + N_ARC_LIST 
				* (2 * ARC_SIZE + SPACING + THICK) 
				+ ARC_SIZE * 4, y);

		y += THICK;
		for (String c : graph.getListSucc().keySet()) {
			PointTime[] pointTimeList = 
					graph.getListSucc().get(c);
			g.drawLine(x + SPACING * 7 + 6 
					* THICK + N_ARC_LIST 
					* (2 * ARC_SIZE 
					+ SPACING + THICK) 
					+ ARC_SIZE * 4, y, 
					x + SPACING * 7 
					+ 6 * THICK + N_ARC_LIST 
					* (2 * ARC_SIZE 
					+ SPACING + THICK) 
					+ ARC_SIZE * 4,
					y + 2 * ARC_SIZE 
					+ 4 * THICK 
					+ 4 * SPACING 
					+ 2 * V_SPACING_TEXT 
					+ fm .getHeight());
			
			g.drawLine(x, y, 
					x,
					y + 2 * ARC_SIZE 
					+ 4 * THICK 
					+ 4 * SPACING 
					+ 2 * V_SPACING_TEXT 
					+ fm .getHeight());
			
			drawArc(g, fm, x + SPACING,
					y + 2 * SPACING 
					+ V_SPACING_TEXT + (3 
					* THICK + fm.getHeight()
					) / 2, c);
			
			x += 2 * SPACING + 2 * ARC_SIZE + THICK;
			
			drawArrow(g, x, y + 2 * SPACING 
					+ V_SPACING_TEXT + (3 
					* THICK + fm.getHeight()
					) / 2 + ARC_SIZE);
			
			x += ARROW_LENGTH + SPACING;
			
			if (pointTimeList.length == 0) {
				drawCross(g, x, y 
						+ SPACING + 2 * V_SPACING_TEXT 
						+ fm.getHeight() + 2 * THICK);
			} else {
				g.drawLine(x, y + SPACING, x,
						y + 3 * SPACING + 4 * THICK
						+ fm.getHeight() + 2 
						* (V_SPACING_TEXT 
						+ ARC_SIZE));
			}
			int i = 1;
			for (PointTime pt : pointTimeList) {
				if (i >= N_ARC_LIST) {
					y += 2 * SPACING 
							+ 4 * THICK 
							+ fm.getHeight() 
							+ 2 * (V_SPACING_TEXT
							+ ARC_SIZE);
					
					x = 4 * SPACING + 2 * ARC_SIZE 
							+ THICK + ARROW_LENGTH;
					
					g.drawLine(SPACING, y, 
							SPACING,
							y + 2 * ARC_SIZE 
							+ 4 * THICK 
							+ 4 * SPACING 
							+ 2 * V_SPACING_TEXT 
							+ fm .getHeight());
					
					g.drawLine(SPACING * 8 + 6 
							* THICK + N_ARC_LIST 
							* (2 * ARC_SIZE 
							+ SPACING + THICK) 
							+ ARC_SIZE * 4, y, 
							SPACING * 8 
							+ 6 * THICK 
							+ N_ARC_LIST 
							* (2 * ARC_SIZE 
							+ SPACING + THICK) 
							+ ARC_SIZE * 4,
							y + 2 * ARC_SIZE 
							+ 4 * THICK 
							+ 4 * SPACING 
							+ 2 * V_SPACING_TEXT 
							+ fm .getHeight());
					
					g.drawLine(SPACING, y, 
							SPACING,
							y + 2 * ARC_SIZE 
							+ 5 * THICK 
							+ 4 * SPACING 
							+ 2 * V_SPACING_TEXT 
							+ fm .getHeight());
					
					g.drawLine(x, y + SPACING, x,
							y + 3 * SPACING 
							+ 4 * THICK
							+ fm.getHeight() + 2 
							* (V_SPACING_TEXT 
							+ ARC_SIZE));
					
					i = 1;
				}
				
				g.drawLine(x, y + SPACING,
						x + 2 * (SPACING + THICK
						+ ARC_SIZE),
						y + SPACING);
				
				String str =
						Integer.toString(pt.getTime());
				
				g.drawString(str,
						x + 2 * (SPACING + THICK
						+ ARC_SIZE 
						- fm.stringWidth(str) / 2) / 2, 
						y + SPACING 
						+ V_SPACING_TEXT 
						+ fm.getHeight() - THICK);
				
				g.drawLine(x, y + SPACING + THICK 
						+ fm.getHeight() 
						+ 2 * V_SPACING_TEXT,
						x + 2 * (SPACING + THICK
						+ ARC_SIZE),
						y + SPACING + THICK 
						+ fm.getHeight() 
						+ 2 * V_SPACING_TEXT);
				
				g.drawLine(x + 2 * (THICK + ARC_SIZE 
						+ SPACING), y + SPACING, 
						x + 2 * (THICK + ARC_SIZE 
						+ SPACING),
						y + 3 * SPACING + 4 * THICK
						+ fm.getHeight() + 2 
						* (V_SPACING_TEXT 
						+ ARC_SIZE));
				
				g.drawLine(x, y + 3 * SPACING 
						+ 4 * THICK + fm.getHeight() 
						+ 2 * (V_SPACING_TEXT
						+ ARC_SIZE),
						x + 2 * (SPACING + THICK
						+ ARC_SIZE),
						y + 3 * SPACING 
						+ 4 * THICK + fm.getHeight() 
						+ 2 * (V_SPACING_TEXT
						+ ARC_SIZE));
				
				drawArc(g, fm, x + THICK + SPACING,
						y + THICK + fm.getHeight() + 2 
						* (V_SPACING_TEXT + SPACING),
						pt.getPoint());
				x += 2 * (SPACING + THICK + ARC_SIZE);
				++i;
			}
			y += 3 * SPACING 
					+ 4 * THICK 
					+ fm.getHeight() 
					+ 2 * (V_SPACING_TEXT
					+ ARC_SIZE);
			x = SPACING;
		}
		
		y += SPACING;
		
		g.drawLine(x, y, x + SPACING * 7 + 6 * THICK + N_ARC_LIST 
				* (2 * ARC_SIZE + SPACING + THICK) 
				+ ARC_SIZE * 4, y);
		
		realWidth = x + SPACING * 8 + 7 * THICK + N_ARC_LIST 
				* (2 * ARC_SIZE + SPACING + THICK) 
				+ ARC_SIZE * 4;
		
		realHeight = y + SPACING + THICK;
	}
	
	private void drawCross(Graphics g, int x, int y) {
		final int cross = ARC_SIZE;
		
		g.drawOval(x, y, 2 * ARC_SIZE, 2 * ARC_SIZE);
		g.drawLine(x + cross / 2, 
				y + cross / 2, 
				x + cross + cross / 2, 
				y + cross + cross / 2);
		g.drawLine(x + cross + cross / 2, 
				y + cross / 2, 
				x + cross / 2, 
				y + cross + cross / 2);
	}
	
	private void drawArrow(Graphics g, int x, int y) {
		g.drawLine(x, y, x + ARROW_LENGTH, y);
		int[] xPoints = {
			x + ARROW_LENGTH,
			x + ARROW_LENGTH - SPACING,
			x + ARROW_LENGTH - SPACING	
		};
		int [] yPoints = {
				y,
				y - SPACING / 2,
				y + SPACING / 2
		};
		g.fillPolygon(xPoints, yPoints, 3);
	}

	// SCROLLABLE
	
	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return new Dimension(PREFERED_WIDTH, PREFERED_HEIGHT);
	}

	@Override
	public int getScrollableUnitIncrement(
			Rectangle visibleRect, int orientation, int direction) {
		return 10;
	}

	@Override
	public int getScrollableBlockIncrement(
			Rectangle visibleRect, int orientation, int direction) {
		return 10;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		return true;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		return realHeight == PREFERED_HEIGHT;
	}
}
