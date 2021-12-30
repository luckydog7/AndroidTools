package org.haxe.extension;


import android.app.Activity;
import android.content.res.AssetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;
import android.os.Environment;

import com.google.gson.Gson;

import org.haxe.lime.HaxeObject;
import java.lang.Object;


/* 
	You can use the Android Extension class in order to hook
	into the Android activity lifecycle. This is not required
	for standard Java code, this is designed for when you need
	deeper integration.
	
	You can access additional references from the Extension class,
	depending on your needs:
	
	- Extension.assetManager (android.content.res.AssetManager)
	- Extension.callbackHandler (android.os.Handler)
	- Extension.mainActivity (android.app.Activity)
	- Extension.mainContext (android.content.Context)
	- Extension.mainView (android.view.View)
	
	You can also make references to static or instance methods
	and properties on Java classes. These classes can be included 
	as single files using <java path="to/File.java" /> within your
	project, or use the full Android Library Project format (such
	as this example) in order to include your own AndroidManifest
	data, additional dependencies, etc.
	
	These are also optional, though this example shows a static
	function for performing a single task, like returning a value
	back to Haxe from Java.
*/
public class AndroidTools extends Extension {
	public static Gson gson = new Gson();

	public static void requestPermissions (String p[], int reqcode) {
		try {
			Extension.mainActivity.requestPermissions(p, reqcode);
		} catch (Exception e){
			Log.e("AndroidTools", e.toString());
		}
	}

	public static String getExternalStorageDirectory(){
		return Environment.getExternalStorageDirectory().getPath();
	}

	public static void goToSettings() {
		Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + Extension.packageName));
		myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
		myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Extension.mainActivity.startActivityForResult(myAppSettings, 168);
	}

	// source https://stackoverflow.com/questions/37294242/how-to-get-all-granted-permissions-of-a-app
	public static String[] getGrantedPermissions() {
		List<String> granted = new ArrayList<String>();
		try {
			PackageInfo pi = Extension.mainContext.getPackageManager().getPackageInfo(Extension.mainContext.getPackageName(), PackageManager.GET_PERMISSIONS);
			for (int i = 0; i < pi.requestedPermissions.length; i++) {
				if ((pi.requestedPermissionsFlags[i] & PackageInfo.REQUESTED_PERMISSION_GRANTED) != 0) {
					granted.add(pi.requestedPermissions[i]);
				}
			}
		} catch (Exception e) {
			Log.e("AndroidTools", e.toString());
		}
		return granted.toArray(new String[granted.size()]);
	}

	public static void openFileManager(String action, String dir, String type, String title, int reqcode){
		try {
			Intent intent = new Intent(action);
			Uri uri = Uri.parse(dir);
			intent.setDataAndType(uri, type);
			Extension.mainActivity.startActivityForResult(Intent.createChooser(intent, title), reqcode);
		}catch (Exception e){
			Log.e("AndroidTools", e.toString());
		}
	}

	public static void toast(String text, int duration){
		try {
			Toast.makeText(Extension.mainContext, text, duration).show();
		}catch (Exception e){
			Log.e("AndroidTools", e.toString());
		}
	}

	public static void installapk(String path){
		try {
			File file1 = new File(path);
			Uri contentUri1 = Uri.fromFile(file1);
			Intent intent = new Intent(Intent.ACTION_VIEW, contentUri1);
			intent.setDataAndType(contentUri1, "application/vnd.android.package-archive");
			intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			Extension.mainActivity.startActivity(intent);
		}catch (Exception e) {
			Log.e("AndroidTools", e.toString());
		}
	}

	public static HaxeObject callback;

	public static void setCallback(final HaxeObject _callback) {
		callback = _callback;
	}
	
	public static int sampleMethod (int inputValue) {
		
		return inputValue * 100;
		
	}

	public static String objectToJson(Object obj){
		try {
			return gson.toJson(obj);
		}catch (Exception e){
			Log.d("AndroidTools", e.toString());
			return "{}";
		}
	}
	
	
	/**
	 * Called when an activity you launched exits, giving you the requestCode 
	 * you started it with, the resultCode it returned, and any additional data 
	 * from it.
	 */
	public boolean onActivityResult (int requestCode, int resultCode, Intent data) {
		callback.call("onActivityResult", new Object[] {requestCode, resultCode, data});
		return true;
		
	}

	/**
	 * Called when the activity receives th results for permission requests.
	 */
	public boolean onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		callback.call("onRequestPermissionsResult", new Object[] {requestCode, permissions, grantResults});
		return true;

	}
	
	
	/**
	 * Called when the activity is starting.
	 */
	public void onCreate (Bundle savedInstanceState) {
		
		
		
	}
	
	
	/**
	 * Perform any final cleanup before an activity is destroyed.
	 */
	public void onDestroy () {
		
		
		
	}
	
	
	/**
	 * Called as part of the activity lifecycle when an activity is going into
	 * the background, but has not (yet) been killed.
	 */
	public void onPause () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onStop} when the current activity is being 
	 * re-displayed to the user (the user has navigated back to it).
	 */
	public void onRestart () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onRestart}, or {@link #onPause}, for your activity 
	 * to start interacting with the user.
	 */
	public void onResume () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onCreate} &mdash; or after {@link #onRestart} when  
	 * the activity had been stopped, but is now again being displayed to the 
	 * user.
	 */
	public void onStart () {
		
		
		
	}
	
	
	/**
	 * Called when the activity is no longer visible to the user, because 
	 * another activity has been resumed and is covering this one. 
	 */
	public void onStop () {
		
		
		
	}
	
	
}