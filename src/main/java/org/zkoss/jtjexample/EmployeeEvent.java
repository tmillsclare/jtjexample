package org.zkoss.jtjexample;

import org.zkoss.zk.ui.event.Event;

public class EmployeeEvent extends Event {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3357970626839071425L;
	
	private static final String eventName = "employeeEvent";
	
	private String _modelId;
	private int _eventType, _indexStart, _indexEnd;
	
	public EmployeeEvent(String modelId, int eventType, int indexStart, int indexEnd) {
		super(eventName);
		
		this._modelId = modelId;
		this._eventType = eventType;
		this._indexStart = indexStart;
		this._indexEnd = indexEnd;
	}

	public String getModelId() {
		return _modelId;
	}
	public int getEventType() {
		return _eventType;
	}


	public void setEventType(int eventType) {
		this._eventType = eventType;
	}


	public int getIndexStart() {
		return _indexStart;
	}

	public void setIndexStart(int indexStart) {
		this._indexStart = indexStart;
	}

	public int getIndexEnd() {
		return _indexEnd;
	}

	public void setIndexEnd(int indexEnd) {
		this._indexEnd = indexEnd;
	}

	
	
}
