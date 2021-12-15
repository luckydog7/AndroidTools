package;


import lime.system.CFFI;
import lime.system.JNI;


class AndroidTools {
	
	
	public static function sampleMethod (inputValue:Int):Int {
		
		#if android
		
		var resultJNI = androidtools_sample_method_jni(inputValue);
		var resultNative = androidtools_sample_method(inputValue);
		
		if (resultJNI != resultNative) {
			
			throw "Fuzzy math!";
			
		}
		
		return resultNative;
		
		#else
		
		return androidtools_sample_method(inputValue);
		
		#end
		
	}
	
	
	private static var androidtools_sample_method = CFFI.load ("androidtools", "androidtools_sample_method", 1);
	
	#if android
	private static var androidtools_sample_method_jni = JNI.createStaticMethod ("org.haxe.extension.AndroidTools", "sampleMethod", "(I)I");
	#end
	
	
}