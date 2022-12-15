package sh.tablet.termux;

import sh.tablet.termux.command.*;

import com.termux.shared.termux.TermuxConstants;
import com.termux.shared.termux.TermuxConstants.TERMUX_APP.RUN_COMMAND_SERVICE;
import com.termux.shared.termux.TermuxConstants.TERMUX_APP.TERMUX_SERVICE;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Termux {

	public ReturnValue runCommand(Options opts) {
		Intent intent = new Intent();
		intent.setClassName(TermuxConstants.TERMUX_PACKAGE_NAME, TermuxConstants.TERMUX_APP.RUN_COMMAND_SERVICE_NAME);
		intent.setAction(RUN_COMMAND_SERVICE.ACTION_RUN_COMMAND);
		intent.putExtra(RUN_COMMAND_SERVICE.EXTRA_COMMAND_PATH, "/data/data/com.termux/usr/bin/" + opts.program);
		intent.putExtra(RUN_COMMAND_SERVICE.EXTRA_WORKDIR, opts.workDir);
		intent.putExtra(RUN_COMMAND_SERVICE.EXTRA_COMMAND_LABEL, opts.label);
		intent.putExtra(RUN_COMMAND_SERVICE.EXTRA_BACKGROUND, true);
		if(!opts.stdin.isEmpty()) {
			intent.putExtra(RUN_COMMAND_SERVICE.EXTRA_STDIN, opts.stdin);
		}
		Intent resultServiceIntent = new Intent(null, ResultsService.class);
		int execId = ResultsService.getNextExecutionId();

		resultServiceIntent.putExtra(ResultsService.EXTRA_EXECUTION_ID, execId);
		@SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getService(null, execId, resultServiceIntent, PendingIntent.FLAG_ONE_SHOT);
		intent.putExtra(RUN_COMMAND_SERVICE.EXTRA_PENDING_INTENT, pendingIntent);

		final Bundle resultBundle = resultServiceIntent.getBundleExtra(TermuxConstants.TERMUX_APP.TERMUX_SERVICE.EXTRA_PLUGIN_RESULT_BUNDLE);

		return new ReturnValue(
				resultBundle.getString(TERMUX_SERVICE.EXTRA_PLUGIN_RESULT_BUNDLE_STDOUT, ""),
				resultBundle.getString(TERMUX_SERVICE.EXTRA_PLUGIN_RESULT_BUNDLE_STDERR),
				resultBundle.getInt(TERMUX_SERVICE.EXTRA_PLUGIN_RESULT_BUNDLE_EXIT_CODE),
				resultBundle.getInt(TERMUX_SERVICE.EXTRA_PLUGIN_RESULT_BUNDLE_ERR)
		);
	}
	public String echo(String value) {
		Log.i("Echo", value);
		return value;
	}
}
