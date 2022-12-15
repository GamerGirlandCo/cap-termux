package sh.tablet.termux;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import sh.tablet.termux.command.Options;
import sh.tablet.termux.command.ReturnValue;

@CapacitorPlugin(name = "Termux")
public class TermuxPlugin extends Plugin {

    private final Termux implementation = new Termux();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void runCommand(PluginCall call) throws JSONException {
        if(!call.getData().has("program")) {
            call.reject("program to execute must be specified.");
            return;
        }
        String callProgram = call.getString("program");
        JSArray callArgs = call.getArray("arguments");
        String callLabel = call.getString("label");
        String callStdin = call.getString("stdin").isEmpty() ? null : call.getString("stdin");
        String callWorkDir = call.getString("workDir");
        Options opties = new Options(callProgram, callArgs, callLabel, callWorkDir, callStdin);
        ReturnValue ret = implementation.runCommand(opties);
        call.resolve(new JSObject(new JSONObject((Map) ret).toString(4)));
    }
}
