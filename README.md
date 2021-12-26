# AndroidTools
extension for haxe (in dev lol)

```haxe
import android.*;

// to request permission, use this
AndroidTools.requestPermission(Permissions.READ_EXTERNAL_STORAGE);

// to get granted permissions(string) array use this
AndroidTools.getGrantedPermissions();

// Open file manager
Android Tools.openFileManager(lime.system.System.applicationStorageDirectory);
```
