# dblp-explorer
Citation Network Analysis
This program takes a (large) JSON file that contains the information about the papers, a keyword and an integer tier. It first searches the articles with titles containing the keyword, then it finds the papers that cited the papers in this step. We call them tier-1 papers. For tire-2 papers, we find the papers that were cited by tire-1 papers. More generally, tier-K papers are the papers that are cited in tier-(K-1) papers.
