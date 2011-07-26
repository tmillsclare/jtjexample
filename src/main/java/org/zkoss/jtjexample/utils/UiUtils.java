package org.zkoss.jtjexample.utils;

import org.zkoss.zk.ui.util.Clients;

public class UiUtils {
	public static void showMessage(String message) {
		Clients.alert(message);
	}
}
