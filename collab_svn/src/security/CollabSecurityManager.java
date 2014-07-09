package security;

import java.io.FileDescriptor;
import java.net.InetAddress;

/**
 * Paranoid security manager.
 * Of course, if you can edit this file, or Main.java, you will still be able to circumvent this.
 * Access permissions must be also set at the repository.
 * @author telmo
 */
public class CollabSecurityManager extends SecurityManager {
	
	@Override public void checkAccept(String host, int port) { throw new SecurityException(); }
	@Override public void checkAccess(Thread g) { throw new SecurityException(); }
	@Override public void checkAccess(ThreadGroup g) { throw new SecurityException(); }
	@Override public void checkAwtEventQueueAccess() { throw new SecurityException(); }
	@Override public void checkConnect(String host, int port) { throw new SecurityException(); }
	@Override public void checkConnect(String host, int port, Object context) { throw new SecurityException(); }
	//@Override public void checkCreateClassLoader() { throw new SecurityException(); }
	@Override public void checkDelete(String file) { throw new SecurityException(); }
	@Override public void checkExec(String cmd) { throw new SecurityException(); }
	@Override public void checkExit(int status) { throw new SecurityException();}
	@Override public void checkLink(String lib) { throw new SecurityException(); }
	@Override public void checkListen(int port) { throw new SecurityException(); }
	//@Override public void checkMemberAccess(Class<?> clazz, int which) { throw new SecurityException(); }
	@Override public void checkMulticast(InetAddress maddr) { throw new SecurityException(); }
	@Override public void checkMulticast(InetAddress maddr, byte ttl) { throw new SecurityException(); }
	//@Override public void checkPackageAccess(String pkg) { throw new SecurityException(); }
	@Override public void checkPackageDefinition(String pkg) {throw new SecurityException(); }
	@Override public void checkPrintJobAccess() { throw new SecurityException(); }
	@Override public void checkPropertiesAccess() { throw new SecurityException(); }
	//@Override public void checkPropertyAccess(String key) { throw new SecurityException(); }
	@Override public void checkRead(FileDescriptor fd) { throw new SecurityException(); }
	//@Override public void checkRead(String file) { throw new SecurityException(); }
	@Override public void checkRead(String file, Object context){ throw new SecurityException(); }
	@Override public void checkSecurityAccess(String action) { throw new SecurityException(); }
	@Override public void checkSetFactory() { throw new SecurityException(); }
	@Override public void checkSystemClipboardAccess() { throw new SecurityException(); }
	@Override public boolean checkTopLevelWindow(Object window) { throw new SecurityException(); }
	@Override public void checkWrite(FileDescriptor fd) { throw new SecurityException(); }
	@Override public void checkWrite(String file) { throw new SecurityException(); }
	
}
