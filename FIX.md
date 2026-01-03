# FIX

## Changes
- Added membership opt-in and eligible-amount checks to `Store.calculateMembershipDiscount` so the discount applies only when the user chose membership and there is remaining amount after promotion discount.
- Cleared `gift` at the start of `Store.addOrRemoveCart` to avoid carrying promo gifts across purchase cycles.
- Updated `OutputView.opening` to avoid trailing spaces, print "out of stock" for zero quantity, and append a non-promo line with zero stock when only a promo entry exists.
- Restructured `StoreController.run` to retry cart input on errors and added `askClosingSafely` so `closing()` is only asked after a valid purchase flow.

## Why
- Membership discount must be opt-in and based on the post-promotion eligible amount to match the expected totals.
- The product list output requires a non-promo line for promo-only items and must show zero stock as "out of stock" in the summary.
- Promo gifts should be computed per purchase and not leak into subsequent flows.
- Input validation errors must not trigger the closing prompt, or the test input sequence breaks.
