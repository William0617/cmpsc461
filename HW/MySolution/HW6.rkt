;Q1
;a)
(define (fac n)
  (if (= n 0) 1
      (* n (fac (- n 1)))))
;b)
(define (bind k v al)
  (cons (list k v) al))
  
(define (lookup k al)
  (if (null? al) #f
      (if (equal? k (car (car al))) (car (cdr (car al)))
          (lookup k (cdr al)))))
;c)
(define al '())
;d)
(define (fac_mem n)
  (if (equal? (lookup n al) #f)
      (begin
        (set! al (bind n (fac n) al))
        (fac n))
      (begin
        (display "memoization hit \n")
        (lookup n al))))

;Q2
(define (build_mem f)
    (let ((al '()))
    	(lambda (n)
	       (if (equal? (lookup n al) #f)
                   (begin
                     (set! al (bind n (f n) al))
                     (f n))
                   (begin
                     (display "memoization hit \n")
                     (lookup n al))))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;         Test Cases : Alexandar Devic          ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Test Part 1a
(display "Testing Part 1a : fac")(newline)
(if (= (fac 5) 120) 'pass 'fail)
(if (= (fac 10) 3628800) 'pass 'fail)
(if (= (fac 0) 1) 'pass 'fail)

;;; Test Part 1b
(display "Testing Part 1a : bind / lookup")(newline)
(define al '())
(if (equal? (lookup 1 (bind 1 123 al)) 123) 'pass 'fail)
(if (equal? (lookup 1 (bind 2 123 al)) #f) 'pass 'fail)
(if (null? al) 'pass 'fail)

;;; Test Part 1c
(display "Testing Part 1c : fac_mem")(newline)
(if (= (fac_mem 1) 1) 'pass 'fail)
(if (= (fac_mem 10) 3628800) 'pass 'fail)
(if (= (fac_mem 5) 120) 'pass 'fail)
(display "The following should be memoization hits!")(newline)
(if (= (fac_mem 1) 1) 'pass 'fail)
(if (= (fac_mem 10) 3628800) 'pass 'fail)
(if (= (fac_mem 5) 120) 'pass 'fail)

;;; Test Part 2
(display "Testing Part 2 : build_mem")(newline)
; Some helper functions to be memoized
(define (female n) (cond ((= n 0) 1)((> n 0) (- n (male (female (- n 1)))))(else 'error)))
(define (male n) (cond ((= n 0) 0)((> n 0) (- n (female (male (- n 1)))))(else 'error)))
(define (fib n) (cond ((= n 0) 0)((= n 1) 1)((> n 1) (+ (fib (- n 1)) (fib (- n 2))))(else 'error)))

(define memFac (build_mem fac))
(define memF (build_mem female))
(define memM (build_mem male))
(define memFib (build_mem fib))

(if (= (memFac 5) 120) 'pass 'fail)
(if (= (memFac 10) 3628800) 'pass 'fail)
(if (= (memFac 0) 1) 'pass 'fail)
(if (= (memF 73) 45) 'pass 'fail)
(if (= (memM 51) 32) 'pass 'fail)
(if (= (memFib 20) 6765) 'pass 'fail)


(display "The following should be memoization hits!")(newline)
(if (= (memFac 5) 120) 'pass 'fail)
(if (= (memFac 10) 3628800) 'pass 'fail)
(if (= (memFac 0) 1) 'pass 'fail)
(if (= (memF 73) 45) 'pass 'fail)
(if (= (memM 51) 32) 'pass 'fail)
(if (= (memFib 20) 6765) 'pass 'fail)


      


















