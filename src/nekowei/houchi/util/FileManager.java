package nekowei.houchi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import nekowei.houchi.entity.Mystery;
import android.os.Environment;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;

public class FileManager {

	private static File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
	private static String path = dir.getPath() + File.separator + "houchi.txt";
	
	/* Checks if external storage is available for read and write */
	public static boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	/* Checks if external storage is available to at least read */
	public static boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}
	
	public static void save() {
		File f = new File(path);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		JsonWriter jw = null;
		try {
			fos = new FileOutputStream(f);
			osw = new OutputStreamWriter(fos, "utf-8");
			jw = new JsonWriter(osw);
			jw.setIndent("\t");
			jw.beginObject();
			jw.name("mysTime").value(Mystery.getInstance().getMysTime());
			jw.endObject();
			Log.i("mys", "save");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (jw != null && osw != null && fos != null) {					
					jw.close();
					osw.close();
					fos.close();
				}
			} catch (IOException e) {
			}
		}
	}
	
	public static void load() {
		File f = new File(path);
		FileInputStream fis = null;
		InputStreamReader isr = null;
		JsonReader jr = null;
		try {
			fis = new FileInputStream(f);
			isr = new InputStreamReader(fis, "utf-8");
			jr = new JsonReader(isr);
			jr.beginObject();
			while (jr.hasNext()) {
				String name = jr.nextName();
				if (name.equals("mysTime")) {
					Mystery.getInstance().setMysTime(jr.nextLong());
				}
			}
			jr.endObject();
			Log.i("mys", "load");
		} catch (FileNotFoundException e) {
			Log.e("mys", "file not found:(");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (jr != null && isr != null && fis != null) {
					jr.close();
					isr.close();
					fis.close();
				}
			} catch (IOException e) {
			}
		}
	}
	
}
