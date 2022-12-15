import { WebPlugin } from '@capacitor/core';

import type { TermuxPlugin } from './definitions';

export class TermuxWeb extends WebPlugin implements TermuxPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
