package com.example.electionfeed;

public class Constant {
	
	public static String[] PARTY_LIST = {"BJP", "CONGRESS", "AAP"};
	public static final String BJP = null;
	public static final String CONGRESS = null;
	public static final String AAP = null;
	public static final String PARTY_NAME = null;

	public static final String DUMMY_SENTIMENT = "{\"tweet1\": \"url1\", \"tweet2\": \"url2\",			\"tweet3\": \"url3\",			\"tweet4\": \"url4\",			\"tweet5\": \"url5\"		}";
	
	public static final String BJPAccount = "BJP4India";
	public static final String CongressAccount = "INCIndia";
	public static final String AAPAccount = "Vote4AAP";
	
	public static final String BJPHandle = "bjp";
	public static final String CongressHandle = "congress";
	public static final String AAPHandle = "aap";
	
	public static final int CRITIC  = 1;
	public static final int PUBLIC  = 0;
	
	public static final int POSITIVE = 1;
	public static final int NEGATIVE = -1;
	public static final int NEUTRAL = 0;
	
	public static String getInitialString(String param) { 
		return "<!DOCTYPE html>"+
			"<html><head><meta charset=\"utf-8\"><style>" +
"body {"+
  "font: 10px sans-serif;"+
"}"+
".arc path {"+
  "stroke: #fff;"+
"}"+
"</style>"+
"<script src=\"http://d3js.org/d3.v3.min.js\"></script>"+
"<script>" +
	/// d3 actionsssss		
	"var data_manage;"+
	"var width;"+
	"var height;"+
	"var radius;"+
	"var color;"+
	"var arc;"+
	"var pie;"+

"function init() {"+
	"data_manage=" + param + ";" +
	"width = 400;"+
	"height = 250;"+
	"radius = Math.min(width, height) / 2;"+

	"color = d3.scale.ordinal()"+
		".range(['#FF3333', '#CC0000', '#660000', '#6b486b', '#a05d56', '#d0743c', '#ff8c00']);"+

	"arc = d3.svg.arc()"+
		".outerRadius(radius - 10)"+
		".innerRadius(radius - 70);"+

	"pie = d3.layout.pie()"+
		".sort(null)"+
		".value(function (d) {"+
		"return d.percentage;"+
	"});"+
	
	"var json_string = JSON.stringify(data_manage);"+
	"refreshPage(json_string)"+
	"}"+
	
	"function refreshPage(json_string) {"+
		"var data = JSON.parse(json_string);";
 }
	
	public static String getSecondString(String param) {
		
		return "var svg = d3.select('"+param +"').append('svg')"+
		".attr('width', width)"+
		".attr('height', height)" +
		".append('g')"+
		".attr('transform', 'translate(' + width / 2 + ',' + height / 2 + ')');"+

		"var g = svg.selectAll('.arc')"+
			".data(pie(data))"+
			".enter().append('g')"+
			".attr('class', 'arc');"+

		"g.append('path')"+
			".attr('d', arc)"+
			".style('fill', function (d) {"+
			"return color(d.data.partyname);"+
		"});"+

		"g.append('text')"+
			".attr('transform', function (d) {"+
			"return 'translate(' + arc.centroid(d) + ')';"+
		"})"+
			".attr('dy', '.35em')"+
			".style('text-anchor', 'middle')"+
			".text(function (d) {"+
			"return d.data.partyname;"+
		"});";
	}
		
		public static String getThirdString() { 
			return 
	"}"+
			

"</script>"+
"</head>"+
"<body onload=\"init()\"><div>Positive Feedback</div> <div id=\"positive\"></div> <div>Negative Feedback </div> <div id=\"negative\"></div> <div>Neutral Feedback></div> <div id=\"neutral\"></div> </body></html>";
		}
	
}
	