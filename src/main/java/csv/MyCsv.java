package csv;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;

import promartist.MyUserDto;

public class MyCsv {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(MyCsv.class);
	 
//    private Long id;
//	private String email;
//	private String artistName;
//	private String firstName;
//	private String lastName;
	   public static void writeCities(PrintWriter writer, MyUserDto[] myUsersDto)  {

	        try {

	            ColumnPositionMappingStrategy mapStrategy
	                    = new ColumnPositionMappingStrategy();

	            mapStrategy.setType(MyUserDto[].class);
	            mapStrategy.generateHeader();

	            String[] columns = new String[]{"id", "email", "artistName","firstName", "lastName"};
	            mapStrategy.setColumnMapping(columns);

	            StatefulBeanToCsv btcsv = new StatefulBeanToCsvBuilder(writer)
	                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
	                    .withMappingStrategy(mapStrategy)
	                    .withSeparator(',')
	                    .build();

	            btcsv.write(myUsersDto);
	            
	            System.out.println(btcsv.toString());

	        } catch (CsvException ex) {

	            LOGGER.error("Error mapping Bean to CSV", ex);
	        }
	    }
	   
	   public static void writeCsv(List<MyUserDto> myUsersDto) throws FileNotFoundException, IOException {
	        String fileName = "/home/jeanno/Bureau/file2.csv";

	       try { Writer writer = Files.newBufferedWriter(Paths.get(fileName));
	             {
	                StatefulBeanToCsv<MyUserDto> beanToCsv = new StatefulBeanToCsvBuilder(writer)
	                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
	                        .build();
	                beanToCsv.write(myUsersDto);
	   }
}catch(Exception ex) {
	ex.printStackTrace();
}
	   
}
	   
}
