size 50 50
begin
	cat tom 25 25 north ;
	mouse jerry 45 15 west ;
	repeat 10
		move jerry ;
		move tom ;
	end ;
	clockwise tom ;
	repeat 2
		move jerry ;
		move tom ;
	end ;
	clockwise jerry ;
	clockwise jerry ;
	repeat 4
		move jerry ;
		move tom ;
	end ;
	clockwise jerry ;
	repeat 2
		move jerry 2 ;
		move tom 3 ;
	end ;
	clockwise tom ;
	move jerry ;
	repeat 3	
		move jerry ;
		move tom 2 ;
	end ;
	move jerry ;
	hole 37 23 ;
	move tom 2 ;
halt