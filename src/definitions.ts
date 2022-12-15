export interface TermuxPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
