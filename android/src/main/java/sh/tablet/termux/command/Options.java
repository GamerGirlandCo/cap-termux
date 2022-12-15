package sh.tablet.termux.command;

import com.getcapacitor.JSArray;

import org.json.JSONException;

public class Options {
	public String program;
	public String[] arguments;
	public String stdin;
	public String label;
	public String workDir;

	public Options(String program, JSArray args, String label, String workDir, String stdin) throws JSONException {
		this.program = program;
		this.label = label;
		this.workDir = workDir;
		this.arguments = new String[args.length()];
		for(int i = 0; i < args.length(); i++) {
			this.arguments[i] = args.getString(i);
		}
		this.stdin = stdin;
	}
}
