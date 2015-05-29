#Style 1
bgcolor = '#525252'
bgopacity = 1
fontcolor = '#fff'
green = '#009900'
red = '#d7051d'
font = 'Myriad Set Pro, Helvetica Neue'
radius = '5px'

#Style 2 - Uncomment this section to use this style instead.
#bgcolor = '#000'
#bgopacity = 0.5
#fontcolor = '#fff'
#green = '#5CE62E'
#red = '#FF4D4D'
#font = 'Avenir'
#radius = '0px'

height = '400px'
#Changing position: Position is set by the first 2 lines of the CSS. Change those as you wish.

#Dont touch this line
command: "java -jar csgo-stats.widget/match_info.jar"

#How frequently to search for a new match.. 1000 = 1 second. Default 60s (60000)
refreshFrequency: 30000

#Styling
style: """
  top: 125px
  right: 40px
  font-family: #{font}
  font-weight: 100
  color: #{fontcolor}
  border-radius: #{radius}
  
  a
    color: #{fontcolor}
    text-decoration:none;

  .wrapper 
    border-radius: #{radius}
    margin: 10px
  	background: rgba(#{bgcolor}, #{bgopacity})
  
  .header
    font-size 24px
    position:absolute
    top:-32px;
  .hltv
    position:absolute
    top:-10px
    right:10px
    font-weight:200
    
  .border-left-thin
    border-left: 3px solid #{fontcolor}
    padding-left: 10px
    
  .status-red
    border-left: 10px solid #{red}
    padding-left: 10px
  .status-green
    border-left: 10px solid #{green}
    padding-left: 10px
    
  .hidden
    display: none
 .matches
    height: #{height}
    overflow: scroll
  .match
    margin-bottom: 7px
    .title
      font-size: 22px
      font-weight: 300
    .league
      font-size: 12px
    .date
      font-size: 12px
    .maps
      font-size: 12px
      font-weight: 200

"""

render: (matchHTML) -> """
  <div class='wrapper'>
	  <div class='header'>
			<em>Upcoming CSGO Matches</em>
	  </div>
    <div class='hltv'>
      <em>powered by hltv.org</em>
    </div>
    <div class='matches'>
      #{matchHTML}
    </div>
  </div>
"""

update: (matchHTML) ->
  $(".matches").html(matchHTML)
  
