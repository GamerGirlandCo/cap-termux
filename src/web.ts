import { WebPlugin } from '@capacitor/core';

import type { CommandOptions, CommandReturnValue, TermuxPlugin } from './definitions';

export class TermuxWeb extends WebPlugin implements TermuxPlugin {
	async runCommand(options: CommandOptions): Promise<CommandReturnValue> {
		console.debug("options", options)
		throw this.unimplemented('Not implemented on web.');
	}
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
