package codemetropolis.toolchain.placing;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import codemetropolis.toolchain.commons.util.FileLogger;
import codemetropolis.toolchain.commons.util.Resources;

public class Main {

	public static void main(String[] args) {
		
		CommandLineOptions options = new CommandLineOptions();
	    CmdLineParser parser = new CmdLineParser(options);

	    try {
	        parser.parseArgument(args);
	        if(options.getInputFile() == null && !options.showHelp())
	        	throw new IllegalArgumentException();
	    } catch (CmdLineException | IllegalArgumentException e) {
	    	String message = Resources.get("command_line_error");
	    	FileLogger.logError(message, e);
	    	System.err.println(message);
	    	System.err.println(Resources.get("placing_usage"));
	    	return;
	    }
	    
	    if(options.showHelp()) {
	    	System.out.println(Resources.get("placing_introduction"));
	    	System.out.println(Resources.get("placing_usage"));
	    	return;
	    }
		
		PlacingExecutor executor = new PlacingExecutor();
	    executor.setPrefix(Resources.get("placing_prefix"));
	    executor.setErrorPrefix(Resources.get("error_prefix"));
		executor.execute(
				new PlacingExecutorArgs(
						options.getInputFile(),
						options.getOutputFile(),
						options.getLayout(),
						options.showMap())
				);
		
	}

}