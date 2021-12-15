package;


import lime.system.CFFI;
import lime.system.JNI;


class AndroidTools {
	public static function requestPermission(perm:Permissions = Permissions.READ_EXTERNAL_STORAGE)
	{
		#if android
		request_permissions_jni([perm], 1);
		#end
	}
	
	
	#if android
	private static var request_permissions_jni = JNI.createStaticMethod ("org.haxe.extension.AndroidTools", "requestPermissions", "([Ljava/lang/String;I)V");
	#end
	
}
