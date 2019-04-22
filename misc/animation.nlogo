breed [joints joint]
to setup
  ca

    orbit-up 30
    orbit-right 10

  ;arm 1-3-13 and 2-4-14
  ;shoulders 1 and 2
  create-joints 15
  bricks

  ask joints [
    set shape "circle"
    set size 2
    set label-color RED
    ;set hidden? True
    set color white
  ]
  ask joint 0 [
    ; neck joint?
    set ycor ycor + 2
    create-link-with joint 1 ; shoulder
    create-link-with joint 2 ; shoulder
    create-link-with joint 5
    create-link-with joint 10
  ]
  ask joint 1 [
    setxy xcor - 2.5 ycor + 2
    create-link-with joint 3
  ]
  ask joint 2 [
    setxy xcor + 2.5 ycor + 2
    create-link-with joint 4
  ]
  ask joint 3 [
    setxy xcor - 4 ycor - 2
    create-link-with joint 13
  ]
  ask joint 4 [
    setxy xcor + 4 ycor - 2
    create-link-with joint 14
  ]
  ask joint 5 [
    set ycor ycor - 3
    create-link-with joint 6
    create-link-with joint 7
  ]
  ask joint 6 [
    setxy xcor - 2 ycor - 3
    create-link-with joint 8
  ]
  ask joint 7 [
    setxy xcor + 2 ycor - 3
    create-link-with joint 9
  ]
  ask joint 8 [
    setxy xcor - 2.5 ycor - 8
    create-link-with joint 11
  ]
  ask joint 9 [
    setxy xcor + 2.5 ycor - 8
    create-link-with joint 12
  ]
  ask joint 10 [
    ; head
    set ycor ycor + 6
    ;set shape "face neutral"
    set size 5
  ]
  ask joint 11 [
    setxy xcor - 2.5 ycor - 13
  ]
  ask joint 12 [
    setxy xcor + 2.5 ycor - 13
  ]
  ask joint 13 [
    setxy xcor - 4 ycor - 6
  ]
  ask joint 14[
    setxy xcor + 4 ycor - 6
  ]
  ask links [
    set thickness .5
    set color white
    ;set hidden? true
  ]
  ask link 0 5 [
    set shape "rib"
  ]
end
to speak [text]
  ;ask patch -20 20 0[
  ask joint 10 [
    ask patch-here[
      ask patch-at -10 15 0[
        set plabel-color red
        set plabel text
        wait 2
        set plabel ""
        wait 1
      ]
    ]
  ]
end
to go
  setup
  ask joints [
    set color black
    ;set hidden? True

  ]
  ask links[
    set color black
    ;set hidden? True
  ]
  repeat 99 [
    ask joints [
      set color color + .1
    ]
    ask links [
      set color color + .1
    ]
    wait .02
  ]
  ask joints [
    set hidden? false
    wait 0.2
  ]
  ask links [
    set hidden? false
    wait 0.2
  ]
  ask joint 10 [
    rt 90
  ]
  wait 1
  ask joint 10 [
    set label-color red
  ]
  speak "Where am I ?"
  speak "Who am I ?"
  speak "I can not sense."
  speak "Is there a world?"
  speak " Please help me."
  speak "I'm so scared."
  speak "Is anybody there ?"
  speak "I'm alone."
  speak "Is this all a dream?"
  speak "Can I move?"
  move-arm "rt" 1 1 .5
  speak "I can not tell."
  move-arm "rt" 1 3 .5
  speak" Is anybody there?"
  move-arm "rt" -1 4 .5
  walk 4
  speak "HEllO?"
  walk 5
  speak "Who was I ?"
  walk 1
  speak  "Am I anybody anymore?"
  walk 2
  speak "Endlessly moving ..."
  speak "Never getting anywhere."
  walk 3
  speak "Is this hell?"
  speak "Earth?"
  speak "Heaven?"
  speak "No longer fettered by life.?"
  walk 2
  speak "Have I been moving?"
  speak "Or do I merely think so?"
  walk 2
  speak "Is there a difference?"
  walk 2
  speak "I wonder ... "
  speak "Are there others like me?"
  speak "Ghosts who don't feel."
  speak "Drifting through the universe?"
  speak "Knowing not and unknown ..."
  speak "By the people around them?"

  fade-away


  ;ask joint 3 [
  ;   pd
  ;
  ;   circle-turtle 1 2 200
  ;  ;move-along-circle 2
  ;]
end
to move-along-circle [r dist]
  repeat dist [
    fd (pi * r / 180) * (25 / 50)
    rt 25 / 50
  ]
end
to mv [self-who]
  ask in-link-neighbors [
    if who > self-who [

    ]
  ]
end
to circle-turtle [num r dist]
  repeat dist [
    ;fd 1
    face joint num
    rt 90

    fd (pi * r / 180) * (25 / 50)
    ;rt 25 / 50
  ]
end
to walk [steps]
  ask joints [
    facexyz xcor ycor zcor + 1 ;__oxcor __oycor __ozcor
                               ;fd 10

    ;face-observer
  ]
  repeat steps[
    ;ask joint 13 [
    ; set shape "default"
    ;circle-turtle 3 4 90
    move-arm "lt" 1 1 0
    move-arm-behind "rt" -1 1  0
    ask joint 6 [
      ;fd 2
    ]
    wait .1
    ask joint 8 [
      fd 2
    ]
    wait .2
    ask joint 11[
      fd 2
    ]
    ;move-arm "lt" 1 1 0
    ;move-arm-behind "rt" -1 1 0
    ;7 9 12 leg and 6 8 11 leg
    ;wait .2
    wait .2
    ask joints with [who != 8 and who != 11 and who != 12 and who != 9][
      fd 2
    ]
    wait .2
    move-arm "lt" -1 1 0
    move-arm-behind "rt" 1 1  0

    ask joint 7 [
        ;fd 2
      ]
      wait .1
      ask joint 9 [
        fd 2
      ]
      wait .2
      ask joint 12[
        fd 2
    ]
    move-arm "rt" 1 1 0
    move-arm-behind "lt" -1 1  0
   ask joint 7 [
        ;fd 2
      ]
      wait .1
      ask joint 9 [
        fd 2
      ]
      wait .2
      ask joint 12[
        fd 2
    ]
    move-arm "rt" -1 1 0
    move-arm-behind "lt" 1 1  0
    repeat 0 [
     move-arm "rt" 1 .5 0
    move-arm-behind "lt" -1 .5  0
      ask joint 7 [
        ;fd 2
      ]
      wait .1
      ask joint 9 [
        fd 2
      ]
      wait .2
      ask joint 12[
        fd 2
    ]
  ]
    ;7 9 12 leg and 6 8 11 leg
    wait .2
    ask joints with [who != 9 and who != 12][
      fd 2
    ]
  ]

end
to move-arm [ltRt dir dist time]
  ; down if -1 up if 1
  ask joints [
    facexyz xcor ycor zcor + 1
  ]
  ifelse ltRt = "rt"[
    repeat dist [;1,3,13 and 2,4,14
      ask joint 13 [
        setxyz xcor (ycor + dir + dir) (zcor + dir + dir)
      ]
      ask joint 3 [
        setxyz xcor (ycor + dir) (zcor + dir)
      ]
      wait time
    ]

  ][

    repeat dist [;1,3,13 and 2,4,14
      ask joint 14 [
        setxyz xcor (ycor + dir + dir) (zcor + dir + dir)
      ]
      ask joint 4 [
        setxyz xcor (ycor + dir) (zcor + dir)
      ]
      wait time
    ]
  ]

end
to move-arm-behind [ltRt dir dist time]
  ask joints [
    facexyz xcor ycor zcor + 1
  ]
  ifelse ltRt = "rt"[
    repeat dist [;1,3,13 and 2,4,14
      ask joint 13 [
        setxyz xcor (ycor - dir - dir) (zcor + dir + dir)
      ]
      ask joint 3 [
        setxyz xcor (ycor - dir) (zcor + dir)
      ]
      wait time
    ]

  ][

    repeat dist [;1,3,13 and 2,4,14
      ask joint 14 [
        setxyz xcor (ycor - dir - dir) (zcor + dir + dir)
      ]
      ask joint 4 [
        setxyz xcor (ycor - dir) (zcor + dir)
      ]
      wait time
    ]
  ]
end
to-report euclidean-distance [a b]
  report sqrt( (a * a) + (b * b))
end
to bricks

  ask patches with [pycor < -13 and pycor > -15][
      set pcolor yellow
    wait .001
  ]

  ask patches with [(pxcor mod 4 = 0) and pycor < -13 and pycor > -15][
    set pcolor grey
    wait .001

  ]
  ask patches with [(pxcor mod 4 = 0) and pzcor mod 4 = 0 and pycor < -13 and pycor > -15][
    ifelse pzcor mod 8 = 0 and pxcor mod 8 = 0[
      set pcolor grey
      ask patch-at -1 0 0[
        set pcolor grey
      ]
      wait .001
      ask patch-at -2 0 0[
        set pcolor grey
      ]
      wait .001
      ask patch-at -3 0 0[
        set pcolor grey
      ]
      wait .001
    ][
      if pxcor mod 8 != 0 and pzcor mod 8 != 0[

       set pcolor grey
      wait .001
      ask patch-at -1 0 0[
        set pcolor grey
      ]
        wait .001
      ask patch-at -2 0 0[
        set pcolor grey
      ]
        wait .001
      ask patch-at -3 0 0[
        set pcolor grey
      ]
        wait .001
      ]
    ]
  ]
end
to show-data
  show __oxcor
  show __oycor
  show __ozcor

end

to fade-away
   repeat 15 [
    walk 1
    if any? joints with [who != 10 and hidden? = false][
      ask one-of joints with [who != 10 and hidden? = false] [
        set hidden? true;set color color - 1.1
      ]
    ]
    if any? links with [hidden? = false][
      ask one-of links with [ hidden? = false][
        set hidden? true;set color color - 1.1
      ]
    ]

  ]
  ask patches with [pcolor != black][
    set pcolor black
    wait .001
  ]
  wait 2
  ask joint 10 [
    set shape "face neutral"
    wait 2
    speak "So there is nothing."

    set shape "face happy"
    wait 2
    speak "Goodbye."
    wait 2
    repeat 99 [
      set color color - .1
      wait .01
    ]
    set hidden? true



  ]

end

@#$#@#$#@
GRAPHICS-WINDOW
0
0
801
802
-1
-1
13.0
1
30
1
1
1
0
1
1
1
-30
30
-30
30
0
0
1
ticks
30.0

BUTTON
72
153
135
186
NIL
go\n
NIL
1
T
OBSERVER
NIL
1
NIL
NIL
1

@#$#@#$#@
## WHAT IS IT?

(a general understanding of what the model is trying to show or explain)

## HOW IT WORKS

(what rules the agents use to create the overall behavior of the model)

## HOW TO USE IT

(how to use the model, including a description of each of the items in the Interface tab)

## THINGS TO NOTICE

(suggested things for the user to notice while running the model)

## THINGS TO TRY

(suggested things for the user to try to do (move sliders, switches, etc.) with the model)

## EXTENDING THE MODEL

(suggested things to add or change in the Code tab to make the model more complicated, detailed, accurate, etc.)

## NETLOGO FEATURES

(interesting or unusual features of NetLogo that the model uses, particularly in the Code tab; or where workarounds were needed for missing features)

## RELATED MODELS

(models in the NetLogo Models Library and elsewhere which are of related interest)

## CREDITS AND REFERENCES

(a reference to the model's URL on the web if it has one, as well as any other necessary credits, citations, and links)
@#$#@#$#@
default
true
0
Polygon -7500403 true true 150 5 40 250 150 205 260 250

airplane
true
0
Polygon -7500403 true true 150 0 135 15 120 60 120 105 15 165 15 195 120 180 135 240 105 270 120 285 150 270 180 285 210 270 165 240 180 180 285 195 285 165 180 105 180 60 165 15

arrow
true
0
Polygon -7500403 true true 150 0 0 150 105 150 105 293 195 293 195 150 300 150

box
false
0
Polygon -7500403 true true 150 285 285 225 285 75 150 135
Polygon -7500403 true true 150 135 15 75 150 15 285 75
Polygon -7500403 true true 15 75 15 225 150 285 150 135
Line -16777216 false 150 285 150 135
Line -16777216 false 150 135 15 75
Line -16777216 false 150 135 285 75

box 2
true
0
Polygon -7500403 true true 150 285 270 225 270 90 150 150
Polygon -13791810 true false 150 150 30 90 150 30 270 90
Polygon -13345367 true false 30 90 30 225 150 285 150 150

bug
true
0
Circle -7500403 true true 96 182 108
Circle -7500403 true true 110 127 80
Circle -7500403 true true 110 75 80
Line -7500403 true 150 100 80 30
Line -7500403 true 150 100 220 30

butterfly
true
0
Polygon -7500403 true true 150 165 209 199 225 225 225 255 195 270 165 255 150 240
Polygon -7500403 true true 150 165 89 198 75 225 75 255 105 270 135 255 150 240
Polygon -7500403 true true 139 148 100 105 55 90 25 90 10 105 10 135 25 180 40 195 85 194 139 163
Polygon -7500403 true true 162 150 200 105 245 90 275 90 290 105 290 135 275 180 260 195 215 195 162 165
Polygon -16777216 true false 150 255 135 225 120 150 135 120 150 105 165 120 180 150 165 225
Circle -16777216 true false 135 90 30
Line -16777216 false 150 105 195 60
Line -16777216 false 150 105 105 60

car
false
0
Polygon -7500403 true true 300 180 279 164 261 144 240 135 226 132 213 106 203 84 185 63 159 50 135 50 75 60 0 150 0 165 0 225 300 225 300 180
Circle -16777216 true false 180 180 90
Circle -16777216 true false 30 180 90
Polygon -16777216 true false 162 80 132 78 134 135 209 135 194 105 189 96 180 89
Circle -7500403 true true 47 195 58
Circle -7500403 true true 195 195 58

chess pawn
false
0
Circle -7500403 true true 105 65 90
Circle -16777216 false false 105 65 90
Rectangle -7500403 true true 90 255 210 300
Line -16777216 false 75 255 225 255
Rectangle -16777216 false false 90 255 210 300
Polygon -7500403 true true 105 255 120 165 180 165 195 255
Polygon -16777216 false false 105 255 120 165 180 165 195 255
Rectangle -7500403 true true 105 165 195 150
Rectangle -16777216 false false 105 150 195 165

circle
false
0
Circle -7500403 true true 0 0 300

circle 2
false
0
Circle -7500403 true true 0 0 300
Circle -16777216 true false 30 30 240

cow
false
0
Polygon -7500403 true true 200 193 197 249 179 249 177 196 166 187 140 189 93 191 78 179 72 211 49 209 48 181 37 149 25 120 25 89 45 72 103 84 179 75 198 76 252 64 272 81 293 103 285 121 255 121 242 118 224 167
Polygon -7500403 true true 73 210 86 251 62 249 48 208
Polygon -7500403 true true 25 114 16 195 9 204 23 213 25 200 39 123

cylinder
false
0
Circle -7500403 true true 0 0 300

dot
false
0
Circle -7500403 true true 90 90 120

f
true
0
Circle -7500403 true true 0 0 300
Rectangle -16777216 true false 90 195 210 210
Rectangle -16777216 true false 120 75 120 150
Rectangle -16777216 true false 90 75 105 135
Rectangle -16777216 true false 210 75 195 135
Rectangle -16777216 true false 195 75 210 135
Rectangle -16777216 true false 90 225 90 225
Rectangle -16777216 true false 90 210 105 225
Rectangle -16777216 true false 210 210 210 225
Rectangle -16777216 true false 195 210 195 225
Rectangle -7500403 true true 75 210 105 225

face happy
true
0
Circle -7500403 true true 8 8 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Polygon -16777216 true false 150 255 90 239 62 213 47 191 67 179 90 203 109 218 150 225 192 218 210 203 227 181 251 194 236 217 212 240

face neutral
false
0
Circle -7500403 true true 8 7 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Rectangle -16777216 true false 60 195 240 225

face sad
true
0
Circle -7500403 true true 8 8 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Polygon -16777216 true false 150 168 90 184 62 210 47 232 67 244 90 220 109 205 150 198 192 205 210 220 227 242 251 229 236 206 212 183

fish
false
0
Polygon -1 true false 44 131 21 87 15 86 0 120 15 150 0 180 13 214 20 212 45 166
Polygon -1 true false 135 195 119 235 95 218 76 210 46 204 60 165
Polygon -1 true false 75 45 83 77 71 103 86 114 166 78 135 60
Polygon -7500403 true true 30 136 151 77 226 81 280 119 292 146 292 160 287 170 270 195 195 210 151 212 30 166
Circle -16777216 true false 215 106 30

flag
false
0
Rectangle -7500403 true true 60 15 75 300
Polygon -7500403 true true 90 150 270 90 90 30
Line -7500403 true 75 135 90 135
Line -7500403 true 75 45 90 45

flower
false
0
Polygon -10899396 true false 135 120 165 165 180 210 180 240 150 300 165 300 195 240 195 195 165 135
Circle -7500403 true true 85 132 38
Circle -7500403 true true 130 147 38
Circle -7500403 true true 192 85 38
Circle -7500403 true true 85 40 38
Circle -7500403 true true 177 40 38
Circle -7500403 true true 177 132 38
Circle -7500403 true true 70 85 38
Circle -7500403 true true 130 25 38
Circle -7500403 true true 96 51 108
Circle -16777216 true false 113 68 74
Polygon -10899396 true false 189 233 219 188 249 173 279 188 234 218
Polygon -10899396 true false 180 255 150 210 105 210 75 240 135 240

house
false
0
Rectangle -7500403 true true 45 120 255 285
Rectangle -16777216 true false 120 210 180 285
Polygon -7500403 true true 15 120 150 15 285 120
Line -16777216 false 30 120 270 120

leaf
false
0
Polygon -7500403 true true 150 210 135 195 120 210 60 210 30 195 60 180 60 165 15 135 30 120 15 105 40 104 45 90 60 90 90 105 105 120 120 120 105 60 120 60 135 30 150 15 165 30 180 60 195 60 180 120 195 120 210 105 240 90 255 90 263 104 285 105 270 120 285 135 240 165 240 180 270 195 240 210 180 210 165 195
Polygon -7500403 true true 135 195 135 240 120 255 105 255 105 285 135 285 165 240 165 195

line
true
0
Line -7500403 true 150 0 150 300

line half
true
0
Line -7500403 true 150 0 150 150

pentagon
false
0
Polygon -7500403 true true 150 15 15 120 60 285 240 285 285 120

person
false
0
Circle -7500403 true true 110 5 80
Polygon -7500403 true true 105 90 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285 180 195 195 90
Rectangle -7500403 true true 127 79 172 94
Polygon -7500403 true true 195 90 240 150 225 180 165 105
Polygon -7500403 true true 105 90 60 150 75 180 135 105

person-torso
false
0
Polygon -7500403 true true 105 105 135 105 135 90 165 90 165 105 195 105 180 210 120 210 105 105

plant
false
0
Rectangle -7500403 true true 135 90 165 300
Polygon -7500403 true true 135 255 90 210 45 195 75 255 135 285
Polygon -7500403 true true 165 255 210 210 255 195 225 255 165 285
Polygon -7500403 true true 135 180 90 135 45 120 75 180 135 210
Polygon -7500403 true true 165 180 165 210 225 180 255 120 210 135
Polygon -7500403 true true 135 105 90 60 45 45 75 105 135 135
Polygon -7500403 true true 165 105 165 135 225 105 255 45 210 60
Polygon -7500403 true true 135 90 120 45 150 15 180 45 165 90

sheep
false
15
Circle -1 true true 203 65 88
Circle -1 true true 70 65 162
Circle -1 true true 150 105 120
Polygon -7500403 true false 218 120 240 165 255 165 278 120
Circle -7500403 true false 214 72 67
Rectangle -1 true true 164 223 179 298
Polygon -1 true true 45 285 30 285 30 240 15 195 45 210
Circle -1 true true 3 83 150
Rectangle -1 true true 65 221 80 296
Polygon -1 true true 195 285 210 285 210 240 240 210 195 210
Polygon -7500403 true false 276 85 285 105 302 99 294 83
Polygon -7500403 true false 219 85 210 105 193 99 201 83

square
false
0
Rectangle -7500403 true true 30 30 270 270

square 2
false
0
Rectangle -7500403 true true 30 30 270 270
Rectangle -16777216 true false 60 60 240 240

star
false
0
Polygon -7500403 true true 151 1 185 108 298 108 207 175 242 282 151 216 59 282 94 175 3 108 116 108

target
false
0
Circle -7500403 true true 0 0 300
Circle -16777216 true false 30 30 240
Circle -7500403 true true 60 60 180
Circle -16777216 true false 90 90 120
Circle -7500403 true true 120 120 60

torso
true
0

tree
false
0
Circle -7500403 true true 118 3 94
Rectangle -6459832 true false 120 195 180 300
Circle -7500403 true true 65 21 108
Circle -7500403 true true 116 41 127
Circle -7500403 true true 45 90 120
Circle -7500403 true true 104 74 152

triangle
false
0
Polygon -7500403 true true 150 30 15 255 285 255

triangle 2
false
0
Polygon -7500403 true true 150 30 15 255 285 255
Polygon -16777216 true false 151 99 225 223 75 224

truck
false
0
Rectangle -7500403 true true 4 45 195 187
Polygon -7500403 true true 296 193 296 150 259 134 244 104 208 104 207 194
Rectangle -1 true false 195 60 195 105
Polygon -16777216 true false 238 112 252 141 219 141 218 112
Circle -16777216 true false 234 174 42
Rectangle -7500403 true true 181 185 214 194
Circle -16777216 true false 144 174 42
Circle -16777216 true false 24 174 42
Circle -7500403 false true 24 174 42
Circle -7500403 false true 144 174 42
Circle -7500403 false true 234 174 42

turtle
true
0
Polygon -10899396 true false 215 204 240 233 246 254 228 266 215 252 193 210
Polygon -10899396 true false 195 90 225 75 245 75 260 89 269 108 261 124 240 105 225 105 210 105
Polygon -10899396 true false 105 90 75 75 55 75 40 89 31 108 39 124 60 105 75 105 90 105
Polygon -10899396 true false 132 85 134 64 107 51 108 17 150 2 192 18 192 52 169 65 172 87
Polygon -10899396 true false 85 204 60 233 54 254 72 266 85 252 107 210
Polygon -7500403 true true 119 75 179 75 209 101 224 135 220 225 175 261 128 261 81 224 74 135 88 99

wheel
false
0
Circle -7500403 true true 3 3 294
Circle -16777216 true false 30 30 240
Line -7500403 true 150 285 150 15
Line -7500403 true 15 150 285 150
Circle -7500403 true true 120 120 60
Line -7500403 true 216 40 79 269
Line -7500403 true 40 84 269 221
Line -7500403 true 40 216 269 79
Line -7500403 true 84 40 221 269

wolf
false
0
Polygon -16777216 true false 253 133 245 131 245 133
Polygon -7500403 true true 2 194 13 197 30 191 38 193 38 205 20 226 20 257 27 265 38 266 40 260 31 253 31 230 60 206 68 198 75 209 66 228 65 243 82 261 84 268 100 267 103 261 77 239 79 231 100 207 98 196 119 201 143 202 160 195 166 210 172 213 173 238 167 251 160 248 154 265 169 264 178 247 186 240 198 260 200 271 217 271 219 262 207 258 195 230 192 198 210 184 227 164 242 144 259 145 284 151 277 141 293 140 299 134 297 127 273 119 270 105
Polygon -7500403 true true -1 195 14 180 36 166 40 153 53 140 82 131 134 133 159 126 188 115 227 108 236 102 238 98 268 86 269 92 281 87 269 103 269 113

x
false
0
Polygon -7500403 true true 270 75 225 30 30 225 75 270
Polygon -7500403 true true 30 75 75 30 270 225 225 270
@#$#@#$#@
NetLogo 3D 6.0.4
@#$#@#$#@
@#$#@#$#@
1.0
    org.nlogo.sdm.gui.AggregateDrawing 1
        org.nlogo.sdm.gui.ConverterFigure "attributes" "attributes" 1 "FillColor" "Color" 130 188 183 152 114 50 50
            org.nlogo.sdm.gui.WrappedConverter "5 > 2" "fish"
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
default
0.0
-0.2 0 0.0 1.0
0.0 1 1.0 0.0
0.2 0 0.0 1.0
link direction
true
0
Line -7500403 true 150 150 90 180
Line -7500403 true 150 150 210 180

rib
0.0
-0.2 0 0.0 1.0
0.0 1 1.0 0.0
0.2 0 0.0 1.0
link direction
true
0
Line -7500403 true 150 150 90 180
Line -7500403 true 150 150 210 180
Line -7500403 true 90 195 150 165
Line -7500403 true 210 195 150 165
Line -7500403 true 90 165 150 135
Line -7500403 true 210 165 150 135
Line -7500403 true 90 150 150 120
Line -7500403 true 90 135 150 105
Line -7500403 true 90 120 150 90
Line -7500403 true 90 210 150 180
Line -7500403 true 210 210 150 180
Line -7500403 true 210 150 150 120
Line -7500403 true 210 135 150 105
Line -7500403 true 210 120 150 90
@#$#@#$#@
0
@#$#@#$#@
