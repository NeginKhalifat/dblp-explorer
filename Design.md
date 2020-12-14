# dblp-explorer Design
  # Reading from the sample input file with Gson
    List<Paper> papers = jsonStreamingGsonExample.parse(jsonFilePath);

  
  # searching keyword in all papers to determine tier-1 papers
    List<Paper> filtered_papers = papers.parallelStream().filter(paper -> paper.getTitle().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
        
  # finding all tier-(n-1) papares 
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
