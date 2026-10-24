# Project TODOs

## auth-service
- [ ] Wire real email sending (currently just prints OTP to console) — need Gmail App Password
- [ ] Wire real SMS sending for MOBILE channel (Twilio or MSG91)
- [ ] Test the MOBILE login flow end-to-end once SMS is wired
- [ ] Review Spring Security default config before deployment (currently permitAll on /api/auth/**)

## General
- [ ] Add global exception handlers to every service (done: catalog-service, auth-service)
- [ ] Add a route in api-gateway's application.yaml for every new service as it's built