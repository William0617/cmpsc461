;Question1:Fibonacci function
(define (fib n)
  (cond ((= n 1) 1)
        ((= n 2) 1)
       (else (+ (fib (- n 1)) (fib (- n 2))))))
;Question2:Hofstadter Female and Male sequences
(define (hofstadter-male-female n)
  (letrec ((female (lambda (n)
		     (if (= n 0)
			 1
			 (- n (male (female (- n 1)))))))
	   (male (lambda (n)
		   (if (= n 0)
		       0
		       (- n (female (male (- n 1))))))))
    (let loop ((i 0))
      (if (> i n)
	  '()
	  (cons (cons (female i)
		      (male i))
		(loop (+ i 1)))))))
;Question3:GCD
(define (mygcd a b)
    (if (= b 0)
        a
        (mygcd b (remainder a b))))
;Question4:
(define (ncall n f x)
  (cond ((= n 0) (f x))
       (else (f (+ n 1)))))
;test function add one
(define (addOne x) (+ x 1))
