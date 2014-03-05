package task;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class FileHandler {
	JSONParser _jp;
	String _dir;
	String _csv;
	String _fileName;

	public FileHandler(JSONParser jp, String dir) {
		_jp = jp;
		_dir = dir;
		java.util.Date date = new java.util.Date();
		_fileName = task.Settings.FILE_PREFIX + new Timestamp(date.getTime())
				+ task.Settings.FILE_EXTENSION;
	}

	public String output() {
		try {
			FileWriter writer = new FileWriter(_fileName);
			outputRow(writer, task.Settings.FILE_HEADERS);
			for (Location loc : _jp.fromNative().getResults()) {
				outputRow(writer, loc.toRow());
			}
			writer.flush();
			writer.close();

		} catch (IOException e) {
			if (task.Settings.DEBUG_MODE) {
				e.printStackTrace();
			} else {
				// notify debug service, pass
			}
		}

		return _fileName;
	}

	private void outputRow(FileWriter fw, String[] row) throws IOException {
		for (int i = 0; i < row.length; i++) {
			fw.append(row[i]);
			if (i != row.length - 1) {
				fw.append(',');
			}

		}
		fw.append('\n');
	}
}
