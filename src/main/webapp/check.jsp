<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>    
    <link rel="stylesheet" href="checkout.css">



</head>
<body>
<form action="checkout">
	
                        <div class="form-group">
                            <label for="name">Full Name</label>
                            <input type="text" id="name" name="name" required placeholder="Enter your full name">
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone Number</label>
                            <input type="tel" id="phone" name="phone" required placeholder="Enter your phone number">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" id="email" name="email" required placeholder="Enter your email">
                        </div>
                        <div class="form-group">
                            <label for="address">Delivery Address</label>
                            <textarea id="address" name="address" required placeholder="Enter your complete delivery address"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="instructions">Delivery Instructions (Optional)</label>
                            <textarea id="instructions" name="instructions" placeholder="Any specific instructions for delivery"></textarea>
                        </div>

                        <section class="payment-details">
                            <h2>Payment Method</h2>
                            <div class="payment-options">
                                <div class="payment-option">
                                    <input type="radio" id="card" name="payment" value="card" checked>
                                    <label for="card">
                                        <span class="payment-icon">&#128179;</span>
                                        Credit/Debit Card
                                    </label>
                                </div>

                                <div class="card-details">
                                    <div class="form-group">
                                        <label for="cardNumber">Card Number</label>
                                        <input type="text" id="cardNumber" name="cardNumber" maxlength="19" placeholder="1234 5678 9012 3456">
                                    </div>
                                    <div class="card-extra">
                                        <div class="form-group">
                                            <label for="expiry">Expiry Date</label>
                                            <input type="text" id="expiry" name="expiry" maxlength="5" placeholder="MM/YY">
                                        </div>
                                        <div class="form-group">
                                            <label for="cvv">CVV</label>
                                            <input type="text" id="cvv" name="cvv" maxlength="3" placeholder="123">
                                        </div>
                                    </div>
                                </div>

                                <div class="payment-option">
                                    <input type="radio" id="upi" name="payment" value="upi">
                                    <label for="upi">
                                        <span class="payment-icon">&#128241;</span>
                                        UPI
                                    </label>
                                </div>

                                <div class="upi-details">
                                    <div class="form-group">
                                        <label for="upiId">UPI ID</label>
                                        <input type="text" id="upiId" name="upiId" placeholder="username@bank">
                                    </div>
                                </div>

                                <div class="payment-option">
                                    <input type="radio" id="cod" name="payment" value="cod">
                                    <label for="cod">
                                        <span class="payment-icon">&#128181;</span>
                                        Cash on Delivery
                                    </label>
                                </div>
                            </div>
                        </section>

                        <div class="order-summary">
                            <h2>Order Summary</h2>
                            <div class="summary-items">
                                <div class="summary-item">
                                    <span>Subtotal</span>
                                    <span>&#8377;55.96</span>
                                </div>
                                <div class="summary-item">
                                    <span>Delivery Fee</span>
                                    <span>&#8377;2.99</span>
                                </div>
                                <div class="summary-item">
                                    <span>Tax</span>
                                    <span>&#8377;4.48</span>
                                </div>
                                <div class="summary-item total">
                                    <span>Total</span>
                                    <span>&#8377;63.43</span>
                                </div>
                            </div>
                           
                            <div class="delivery-time">
                                <span class="delivery-icon">&#128666;</span>
                                <span>Estimated delivery time: <%--=r.getEta() --%> minutes</span>
                            </div>
                        </div>
                        <button type="submit" class="place-order-btn">Place Order</button>
                    
</form>

</body>
</html>