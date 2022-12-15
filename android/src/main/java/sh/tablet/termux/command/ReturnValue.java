package sh.tablet.termux.command;

import java.util.Objects;

public class ReturnValue {
	public String stdout;
	public String stderr;
	public int errorCode;
	public int exitCode;
	public ReturnValue(String stdout, String stderr, int exitCode, int errorCode) {
		this.stdout = stdout;
		this.stderr = stderr;
		this.exitCode = exitCode;
		this.errorCode = errorCode;
	}
}