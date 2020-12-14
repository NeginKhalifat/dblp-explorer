import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.gson.stream.JsonToken.END_DOCUMENT;

public class CitationNetworkAnalysis {


    public static void main(String[] args) {
        String keyword = "in";
        int tier = 3;
        CitationNetworkAnalysis jsonStreamingGsonExample = new CitationNetworkAnalysis();
        String jsonFilePath = "sampleInput.json";
        List<Paper> papers = jsonStreamingGsonExample.parse(jsonFilePath);
        //filtered by keyword
        List<Paper> filtered_papers = papers.parallelStream().filter(paper -> paper.getTitle().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
        List<String> next_ref = new ArrayList<String>();
        List<String> finalNext_ref = next_ref;
        filtered_papers.parallelStream().filter(e -> e.getReferences() != null).forEach(e -> {
            finalNext_ref.addAll(e.getReferences());
        });
        System.out.print("Tier: ");
        System.out.println(1);
        filtered_papers.forEach(System.out::println);

        List<Integer> for_loop = Stream.iterate(2, n -> n + 1)
                .limit(tier)
                .collect(Collectors.toList());
        for (Integer e : for_loop) {
            System.out.print("################################################################################\nTier: ");
            System.out.println(e);
            List<String> next = new ArrayList<String>();
            for (String i : next_ref) {
                if (papers.stream().anyMatch(paper -> paper.getId().equals(i))) {
                    papers.parallelStream().filter(paper -> paper.getId().equals(i)).forEach(System.out::println);
                    papers.parallelStream().filter(paper -> paper.getId().equals(i)).filter(p -> p.getReferences() != null).forEach(d -> {
                        next.addAll(d.getReferences());
                    });

                }
            }
            next_ref = next;


        }
    }


    public List<Paper> parse(String jsonFilePath) {
        //create JsonReader object and pass it the json file,json source or json text.
        try (JsonReader jsonReader = new JsonReader(
                new InputStreamReader(
                        new FileInputStream(jsonFilePath), StandardCharsets.UTF_8))) {
            Gson gson = new GsonBuilder().create();
            List<Paper> papers = new ArrayList<Paper>();
            jsonReader.setLenient(true);
            int numberOfRecords = 0;
            while (jsonReader.hasNext() && jsonReader.peek() != END_DOCUMENT) { //next json array element
                Paper paper = gson.fromJson(jsonReader, Paper.class);

                papers.add(paper);

                numberOfRecords++;


            }
            System.out.println("Total Records Found : " + numberOfRecords);
            return papers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}