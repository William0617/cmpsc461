(LEFT (PAIR 3 4))  ; 3 
(RIGHT (PAIR 3 4))  ; 4
(LEFT (PAIR 3 (PAIR 4 5)))  ; 3
(RIGHT (RIGHT (PAIR 3 (PAIR 4 5))))  ; 5 

((funPower sqrt 2) 16) ; 2
((funPower (lambda (x) (* x x)) 3) 2) ; 256
((funPower (lambda (x) (+ 3 x)) 3) 1) ; 10

(depthOfList '()) ; 1
(depthOfList '(1 2 (1 (2 3)))) ; 3
(depthOfList '(0 (0 ()) ())) ; 3

(exptFast 2 3) ; 8
(exptFast 2 10) ; 1024
(exptFast 5 4) ; 625

(allTrue '(#t #t)) ; #t
(allTrue '(#t #f #t)) ; #f
(allTrue '()) ; #t

(sum '(1 2 3 4 5)) ; 15
(sum '("1" "2" "4" "8")) ; "1248"
(sum '((1 2) (3 4 5))) ; (1 2 3 4 5)

(zip '((1 2) (3 4))) ; ((1 3) (2 4)).
(zip '((1 2) (3 4) (5 6) (7 8))) ; ((1 3 5 7) (2 4 6 8)).
(zip '((1 2 3) (4 5 6))) ; ((1 4) (2 5) (3 6)).
