package android;

import lime.system.CFFI;
import lime.system.JNI;

class AndroidTools {
	public static function requestPermission(perm:Permissions = Permissions.READ_EXTERNAL_STORAGE) {
		#if android
		request_permissions_jni([perm], 1);
		#end
	}

	public static function getGrantedPermissions():Array<Permissions> {
		#if android
		return getGrantedPermissions_jni();
		#end
		return null;
	}

	public static function openFileManager(dir:String, title:String = "select a file", type:String = "*/*", action:Intent = Intent.ACTION_GET_CONTENT) {
		#if android
		return openFileManager_jni(action, dir, type, title);
		#end
		return null;
	}

	#if android
	private static var request_permissions_jni = JNI.createStaticMethod("org.haxe.extension.AndroidTools", "requestPermissions", "([Ljava/lang/String;I)V");
	private static var getGrantedPermissions_jni = JNI.createStaticMethod("org.haxe.extension.AndroidTools", "getGrantedPermissions", "()[Ljava/lang/String;");
	private static var openFileManager_jni = JNI.createStaticMethod("org.haxe.extension.AndroidTools", "openFileManager", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
	#end
}
