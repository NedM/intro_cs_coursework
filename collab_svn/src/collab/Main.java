package collab;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.AccessControlException;
import java.util.*;

//import security.CollabSecurityManager;

public class Main {

	/**
	 * Look for all the Java classes in the collab package, attempt to create 
	 * a new instance for each, call its toString method and print it.
	 */
	public static void main(String[] args) {

		//System.setSecurityManager(new CollabSecurityManager());
		
		try {
			Class<?>[] classes = getClasses("collab");
			for (Class<?> c : classes) {
				Object instance;
				try {
					instance = c.newInstance();
					System.out.println(instance.toString());
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Attempts to extract all classes within a given package name
	 * @param packageName
	 * @return a list of Class objects found in the current package
	 * @throws ClassNotFoundException if reflection fails
	 */
	public static Class<?>[] getClasses(String packageName)
		throws ClassNotFoundException {
		
		List<Class<?>> classes = new ArrayList<Class<?>>();

		// Get a File object for the package
		File directory = null;
		
		try {
			
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			if (loader == null)
				throw new ClassNotFoundException("Can't get class loader.");
			
			String path = packageName.replace('.', '/');
			URL resource = loader.getResource(path);
			
			if (resource == null)
				throw new ClassNotFoundException("No resource for " + path);
			
			try {
				
				String decoded = URLDecoder.decode(resource.getFile(), "UTF-8");
				directory = new File(decoded);
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		} catch (NullPointerException x) {
			throw new ClassNotFoundException(packageName + " (" + directory
					+ ") does not appear to be a valid package");
		}
		
		if (directory.exists()) {
			
			// Get the list of the files contained in the package
			String[] files = directory.list();
			for (int i = 0; i < files.length; i++) {
				// we are only interested in .class files
				if (files[i].endsWith(".class")) {
					// removes the .class extension
					classes.add(Class.forName(packageName + '.'
							+ files[i].substring(0, files[i].length() - 6)));
				}
			}
			
		} else {
			throw new ClassNotFoundException(packageName
					+ " does not appear to be a valid package");
			
		}
		
		Class<?>[] classesA = new Class[classes.size()];
		classes.toArray(classesA);
		return classesA;
	}
	
	
	@Override
	public String toString() {
		return "Main class string";
	}

}
