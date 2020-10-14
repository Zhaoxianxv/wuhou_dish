package com.yfy.charting_mp.listener;

import com.yfy.charting_mp.data.DataSet;
import com.yfy.charting_mp.data.Entry;

/**
 * Listener for callbacks when drawing on the chart.
 * 
 * @author Philipp
 * 
 */
public interface OnDrawListener {

	/**
	 * Called whenever an entry is added with the finger. Note this is also called for entries that are generated by the
	 * library, when the touch gesture is too fast and skips points.
	 * 
	 * @param entry
	 *            the last drawn entry
	 */
	public void onEntryAdded(Entry entry);

	/**
	 * Called whenever an entry is moved by the user after beeing highlighted
	 * 
	 * @param entry
	 */
	public void onEntryMoved(Entry entry);

	/**
	 * Called when drawing finger is lifted and the draw is finished.
	 * 
	 * @param dataSet
	 *            the last drawn DataSet
	 */
	public void onDrawFinished(DataSet<?> dataSet);

}
