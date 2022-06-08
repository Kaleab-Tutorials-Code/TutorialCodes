package c_fields_modification_and_arrays_creation_implement_file_parser.exercise5_generalpurposeconfigurationparser;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Scanner;

import c_fields_modification_and_arrays_creation_implement_file_parser.exercise5_generalpurposeconfigurationparser.configloader.ConfigParserBasic;
import c_fields_modification_and_arrays_creation_implement_file_parser.exercise5_generalpurposeconfigurationparser.configloader.ConfigParserWithArraySupport;
import c_fields_modification_and_arrays_creation_implement_file_parser.exercise5_generalpurposeconfigurationparser.data.GameConfig;
import c_fields_modification_and_arrays_creation_implement_file_parser.exercise5_generalpurposeconfigurationparser.data.SampleObjectWithArray;
import c_fields_modification_and_arrays_creation_implement_file_parser.exercise5_generalpurposeconfigurationparser.data.UserInterfaceConfig;


public class Main {

	private static final Path GAME_CONFIG_PATH = Path.of("resources/game-properties.cfg");
	private static final Path USER_CONFIG_PATH = Path.of("resources/user-interface.cfg");
	private static final Path CONFIG_WITH_ARRAY_VALUES = Path.of("resources/config-with-array-values.cfg");
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
		GameConfig config = ConfigParserBasic.createConfigObject(GameConfig.class, GAME_CONFIG_PATH);
		UserInterfaceConfig userConfig = ConfigParserBasic.createConfigObject(UserInterfaceConfig.class, USER_CONFIG_PATH);
		SampleObjectWithArray scwa = ConfigParserWithArraySupport.createConfigObject(SampleObjectWithArray.class, CONFIG_WITH_ARRAY_VALUES);
		
		
		System.out.println(config);
		System.out.println(userConfig);
		System.out.println(scwa);
	}
	
	
	
	

}
