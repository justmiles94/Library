package com.tools.library;

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.awt.desktop.AppForegroundEvent;
import java.awt.desktop.AppForegroundListener;
import java.awt.desktop.AppHiddenEvent;
import java.awt.desktop.AppHiddenListener;
import java.awt.desktop.AppReopenedEvent;
import java.awt.desktop.AppReopenedListener;
import java.awt.desktop.ScreenSleepEvent;
import java.awt.desktop.ScreenSleepListener;
import java.awt.desktop.SystemSleepEvent;
import java.awt.desktop.SystemSleepListener;
import java.awt.desktop.UserSessionEvent;
import java.awt.desktop.UserSessionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Desk {

	public static void main(String[] args) throws InterruptedException {
		if (!Desktop.isDesktopSupported()) {
			System.out.println("Desktop is not supported");
			return;
		}
		Desktop desk = Desktop.getDesktop();
		for (Action act : Desktop.Action.values()) {
			if (desk.isSupported(act)) {
				System.out.println(act.name() + " is supported");
			} else {
				System.out.println(act.name() + " is not supported");
			}
		}
		desk.addAppEventListener(appForegroundListener);
		desk.addAppEventListener(userSessionListener);
		desk.addAppEventListener(systemSleepListener);
		desk.addAppEventListener(screenSleepListener);
		desk.addAppEventListener(appReopenedListener);
		desk.addAppEventListener(appHiddenListener);
		try {
			desk.browse(new URI("http://google.com"));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		Thread.sleep(100*1000);//100 seconds
		System.out.println("desk closed?");
	}

	static AppForegroundListener appForegroundListener = new AppForegroundListener() {

		@Override
		public void appRaisedToForeground(AppForegroundEvent e) {
			System.out.println("appRaisedToForeground");

		}

		@Override
		public void appMovedToBackground(AppForegroundEvent e) {
			System.out.println("appMovedToBackground");

		}

	};
	static UserSessionListener userSessionListener = new UserSessionListener() {

		@Override
		public void userSessionDeactivated(UserSessionEvent e) {
			System.out.println(e);
		}

		@Override
		public void userSessionActivated(UserSessionEvent e) {
			System.out.println(e);
		}

	};
	static SystemSleepListener systemSleepListener = new SystemSleepListener() {

		@Override
		public void systemAboutToSleep(SystemSleepEvent e) {
			System.out.println(e);
		}

		@Override
		public void systemAwoke(SystemSleepEvent e) {
			System.out.println(e);
		}

	};
	static ScreenSleepListener screenSleepListener = new ScreenSleepListener() {

		@Override
		public void screenAboutToSleep(ScreenSleepEvent e) {
			System.out.println(e);
		}

		@Override
		public void screenAwoke(ScreenSleepEvent e) {
			System.out.println(e);
		}

	};
	static AppReopenedListener appReopenedListener = new AppReopenedListener() {

		@Override
		public void appReopened(AppReopenedEvent e) {
			System.out.println(e);
		}

	};
	static AppHiddenListener appHiddenListener = new AppHiddenListener() {

		@Override
		public void appHidden(AppHiddenEvent e) {
			System.out.println(e);
		}

		@Override
		public void appUnhidden(AppHiddenEvent e) {
			System.out.println(e);
		}

	};

}
