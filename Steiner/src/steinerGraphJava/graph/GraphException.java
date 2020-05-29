package steinerGraphJava.graph;

public class GraphException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1771650927177379822L;
	
	private ErrorType error;
	
	public enum ErrorType {
		INSERT_NODE_ALREADY_IN,
		RENAME_BUT_NODE_NOT_FOUND,
		REMOVE_NODE_NOT_FOUND,
		REMOVE_ARC_NOT_FOUND,
		ADD_ELEMENT_INVALID_SYNTAX,
		IO_ERROR,
		SEE_ALREADY_ORIGINAL,
		BIG_BAD_ERROR,
		SOLVE_ARRAY_OUT_OF_BOUNDS
	}

	public GraphException(String string, ErrorType err) {
		super(string);
		error = err;
	}
	
	public ErrorType getError() {
		return error;
	}

}
