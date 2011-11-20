package org.rstudio.studio.client.common.shell;

import org.rstudio.core.client.jsonrpc.RpcObjectList;
import org.rstudio.studio.client.workbench.model.ConsoleAction;
import org.rstudio.studio.client.workbench.views.console.shell.editor.InputEditorDisplay;

import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public interface ShellDisplay extends HasKeyPressHandlers
{
   void consoleWriteError(String string) ;
   void consoleWriteOutput(String output) ;
   void consoleWriteInput(String input);
   void consoleWritePrompt(String prompt);
   void consolePrompt(String prompt) ;
   void ensureInputVisible() ;
   InputEditorDisplay getInputEditorDisplay() ;
   void clearOutput() ;
   String processCommandEntry() ;
   void setFocus(boolean focused) ;
   int getCharacterWidth() ;
   boolean isPromptEmpty();

   void playbackActions(RpcObjectList<ConsoleAction> actions);

   void setMaxOutputLines(int maxLines);

   HandlerRegistration addCapturingKeyDownHandler(KeyDownHandler handler);
}