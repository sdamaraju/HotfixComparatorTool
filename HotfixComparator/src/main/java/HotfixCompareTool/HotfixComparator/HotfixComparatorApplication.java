/**
 * @author damas
 *
 * 
 */
package HotfixCompareTool.HotfixComparator;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HotfixComparatorApplication {

	public static void main(String[] args){
		SpringApplication.run(HotfixComparatorApplication.class, args);
	}
		
	@GetMapping("/{path1}/{path2}") // deprecated.
	public String runCompare(@PathVariable("path1") String path1,@PathVariable("path2") String path2) throws IOException{
		String p1 = path1.replace("*", "\\");
		String p2 = path2.replace("*", "\\"); // hardcoded. replace with json code tomorrow.
		System.out.println(p1);
		System.out.println(p2);
		//read csv files 
		File env1 = new File(p1);
		File env2 = new File(p2);
		ArrayList listOfHotfixSet = new ArrayList(2); // dynamic value for number of hotfixfiles that can be compared in Beta version, as of now hard-coding it to 2.
		listOfHotfixSet = HotfixUtil.createNecessaryHotfixObjects(env1,env2,listOfHotfixSet);
		ResultGenerator rgen = new ResultGenerator();
		String str = rgen.compareHotfixesAndGenerateResults(listOfHotfixSet);
		return str;
	}
	
	@RequestMapping(value = "/compareHotfixes", method = RequestMethod.POST)
	public @ResponseBody String compareHotfix(@RequestBody Paths paths) throws IOException {
		
		String p1= (paths.getPath1());
		String p2= (paths.getPath2());
		File env1 = new File(p1);
		File env2 = new File(p2);
		ArrayList listOfHotfixSet = new ArrayList(2); // dynamic value for number of hotfixfiles that can be compared in Beta version, as of now hard-coding it to 2.
		listOfHotfixSet = HotfixUtil.createNecessaryHotfixObjects(env1,env2,listOfHotfixSet);
		ResultGenerator rgen = new ResultGenerator();
		String str = rgen.compareHotfixesAndGenerateResults(listOfHotfixSet);
		return str;
	}	 
}
