export interface TermuxPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
	runCommand(options: CommandOptions): Promise<CommandReturnValue>;
}

export interface CommandOptions {
	program: string;
	arguments: string[];
	stdin?: string;
	label?: string;
	workDir?: string;
}

export interface CommandReturnValue {
	exitCode: number;
	errorCode: number;
	stdout: string;
	stderr: string;
}
