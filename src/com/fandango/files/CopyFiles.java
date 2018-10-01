package com.fandango.files;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CopyFiles {

    static class Pair{
        String key;
        String value;
        private Pair(String key, String value){
            this.key = key;
            this.value = value;
        }
    }
    
    private static final String ROOT_PATH = "C:\\Users\\oscar.manzo\\Globant\\Fandango\\Project\\pos-service\\src\\wiremock\\resources\\com\\fandango\\commerce";

    private static final String ROOT_DEST_PATH = "C:\\Users\\oscar.manzo\\Globant\\Fandango\\Temp\\posresponse\\__files";

    private static final List<Pair> FILES = new ArrayList<>();

    public static void main(String[] args){
        CopyFiles app = new CopyFiles();
        app.run();
    }

    private void run() {
        FILES.forEach(item -> findAndCopy(new File(ROOT_PATH + item.key),
                                            new File(ROOT_DEST_PATH + item.value)));
    }

    private void findAndCopy(File source, File dest){
        if (!source.exists()) {
            System.out.println("Not exist"+ source.getPath());
            return;
        }
        if (dest.exists()) {
            System.out.println("Already exist"+ dest.getPath());
            return;
        }

        copy(source, dest);
    }

    private void copy(File source, File dest){
        try {
            findOrCreateParent(dest.getParentFile());

            Path copy = Files.copy(source.toPath(), dest.toPath());
            //print(source);
            //print(copy.toFile());
            //System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findOrCreateParent(File file){
        try{
            if (!file.exists()){
                findOrCreateParent(file.getParentFile());

                Path path = file.toPath();
                Files.createDirectory(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void print(File file){
        try {
            String path = file.getPath();
            System.out.println(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        FILES.add(new Pair("/jsonresponse/amc/addLoyaltyToOrder.json", "/amc/success/order_200_20482_add-loyalty.json"));
        FILES.add(new Pair("/jsonresponse/amc/addLoyaltyToOrder.json", "/amc/success/order_200_1202_add-loyalty.json"));
        FILES.add(new Pair("/jsonresponse/amc/addLoyaltyToOrder.json", "/amc/success/order_200_1201_add-loyalty.json"));
        FILES.add(new Pair("/jsonresponse/amc/addLoyaltyToOrder.json", "/amc/success/order_200_323_add-loyalty.json"));
        FILES.add(new Pair("/jsonresponse/amc/amcExpiredOrderResponse.json", "/amc/success/order_200_20_experired.json"));
        FILES.add(new Pair("/jsonresponse/amc/amcOrderResponse.json", "/amc/success/order_200_323_get.json"));
        FILES.add(new Pair("/jsonresponse/amc/amcOrderResponse.json", "/amc/success/order_200_1201_get.json"));
        FILES.add(new Pair("/jsonresponse/amc/amcOrderResponse.json", "/amc/success/order_200_20481_get.json"));
        FILES.add(new Pair("/jsonresponse/amc/amcOrderResponse.json", "/amc/success/order_200_20482_get.json"));
        FILES.add(new Pair("/jsonresponse/amc/amcReserveOrder404Request.json", "/amc/error/order_404_XXX_reserve.json"));
        FILES.add(new Pair("/jsonresponse/amc/applyPayment.json", "/amc/error/payment_400_20_apply.json"));
        FILES.add(new Pair("/jsonresponse/amc/applyPayment.json", "/amc/error/payment_201_322_apply.json"));
        FILES.add(new Pair("/jsonresponse/amc/applyPayment.json", "/amc/error/payment_201_323_apply.json"));
        FILES.add(new Pair("/jsonresponse/amc/applyPayment.json", "/amc/error/payment_201_1201_apply.json"));
        FILES.add(new Pair("/jsonresponse/amc/applyPayment.json", "/amc/error/payment_201_1202_apply.json"));
        FILES.add(new Pair("/jsonresponse/amc/applyPayment.json", "/amc/error/payment_201_20482_apply.json"));
        FILES.add(new Pair("/jsonresponse/amc/applyPaymentWithoutCCInfo.json", "/amc/success/payment_201_20481_apply-without-cc-info.json"));
        FILES.add(new Pair("/jsonresponse/amc/posServiceReserveRequest404.json", "/amc/error/payment_404_194979181_service-reserve.json"));
        FILES.add(new Pair("/jsonresponse/retriever/retrieverGAValidConfirmOrderRequestV3.xml", "/retriver/success/order_200_XXX_reserve-ga-valid-confirm.xml"));
        FILES.add(new Pair("/jsonresponse/retriever/retrieverInvalidConfirmOrderRequest.xml", "/retriver/error/order_400_XXX_invalid-confirm.xml"));
        FILES.add(new Pair("/jsonresponse/retriever/retrieverReserveGA.xml", "/retriver/success/reserve_200_XXX_ga-order.xml"));
        FILES.add(new Pair("/jsonresponse/retriever/retrieverReserveGA2500.xml", "/retriver/success/reserve_200_2500_ga-order.xml"));
        FILES.add(new Pair("/jsonresponse/retriever/retrieverReserveGAInvalid.xml", "/retriver/error/reserve_400_XXX_ga-invalid.xml"));
        FILES.add(new Pair("/jsonresponse/retriever/retrieverReserveReserved.xml", "/retriver/success/reserve_200_XXX_reserved_seat.xml"));
        FILES.add(new Pair("/jsonresponse/retriever/retrieverValidConfirmOrderRequest.xml", "/retriver/success/order_200_XXX_confirm-successful.xml"));
        FILES.add(new Pair("/jsonresponse/retriever/retrieverValidConfirmOrderRequestV3.xml", "/retriver/success/order_200_XXX_confirm.xml"));
        FILES.add(new Pair("/posresponse/__files/amc/availability/invalid/404.json", "/amc/error/availability_404_1500_ga-showtime.json"));
        FILES.add(new Pair("/posresponse/__files/amc/availability/invalid/500.json", "/amc/error/availability_500_1501_ga-showtime.json"));
        FILES.add(new Pair("/posresponse/__files/amc/availability/invalid/503.json", "/amc/error/availability_503_1502_ga-showtime.json"));
        FILES.add(new Pair("/posresponse/__files/amc/availability/amcValidCanceledResponse.json", "/amc/success/availability_200_1401_canceled-showtime.json"));
        FILES.add(new Pair("/posresponse/__files/amc/availability/amcValidGAResponse.json", "/amc/success/availability_200_1201_ga-showtime.json"));
        FILES.add(new Pair("/posresponse/__files/amc/availability/amcValidReservedResponse.json", "/amc/success/availability_200_1200_valid-showtime.json"));
        FILES.add(new Pair("/posresponse/__files/amc/availability/amcValidReservedResponse300.json", "/amc/success/availability_200_1300_valid-showtime.json"));
        FILES.add(new Pair("/posresponse/__files/amc/availability/amcValidSoldoutResponse.json", "/amc/success/availability_200_1400_sold-out-showtime.json"));
        FILES.add(new Pair("/posresponse/__files/amc/order/getFulfilledOrder_4000268.json", "/amc/success/order_200_4000268_fulfilled.json"));
        FILES.add(new Pair("/posresponse/__files/amc/order/getFulfilledOrder_4000269.json", "/amc/success/order_200_4000269_fulfilled.json"));
        FILES.add(new Pair("/posresponse/__files/amc/order/getFulfilledOrder_4000270.json", "/amc/success/order_200_4000270_fulfilled.json"));
        FILES.add(new Pair("/posresponse/__files/amc/order/getFulfilledOrder_4000271.json", "/amc/success/order_200_4000271_fulfilled.json"));
        FILES.add(new Pair("/jsonresponse/amc/applyPayment.json", "/amc/error/order_400_20_apply_payment.json"));
        FILES.add(new Pair("/jsonresponse/amc/applyPayment.json", "/amc/success/order_201_322_apply_payment.json"));
        FILES.add(new Pair("/jsonresponse/amc/applyPayment.json", "/amc/success/order_201_323_apply_payment.json"));
        FILES.add(new Pair("/jsonresponse/amc/applyPayment.json", "/amc/success/order_201_1201_apply_payment.json"));
        FILES.add(new Pair("/jsonresponse/amc/applyPayment.json", "/amc/success/order_201_1202_apply_payment.json"));
        FILES.add(new Pair("/jsonresponse/amc/applyPayment.json", "/amc/success/order_201_20481_apply_payment.json"));
        FILES.add(new Pair("/jsonresponse/amc/applyPayment.json", "/amc/success/order_201_20482_apply_payment.json"));
        //FILES.add(new Pair("/posresponse/__files/amc/order/getOrder.json", "/amc//");
        FILES.add(new Pair("/posresponse/__files/amc/refund/amcAlreadyRefundedResponse.json", "/amc/error/refund_500_XXX_already-refunded-order.json"));
        FILES.add(new Pair("/posresponse/__files/amc/refund/amcPartiallyRefundedOrder_20500.json", "/amc/success/refund_201_20500_partially-refunded-order.json"));
        FILES.add(new Pair("/posresponse/__files/amc/refund/amcRefundedOrderResponse.json", "/amc/success/refund_201_20483_refund-already-refunded.json"));
        FILES.add(new Pair("/posresponse/__files/amc/refund/amcRefundInProgressOrder_20501.json", "/amc/success/refund_201_20501_refund-in-progress-order.json"));
        FILES.add(new Pair("/posresponse/__files/amc/refund/amcRefundInvalidOrderResponse.json", "/amc/error/refund_201_99999_refund-invalid-order.json"));
        FILES.add(new Pair("/posresponse/__files/amc/refund/amcRefundOrderResponse.json", "/amc/success/refund_200_20484_request-refund-order.json"));
        FILES.add(new Pair("/posresponse/__files/amc/refund/amcValidRefundResponse.json", "/amc/success/refund_200_XXX_refund-order.json"));
        FILES.add(new Pair("/posresponse/__files/amc/reserve/amcOrderDiscountResponse.json", "/amc/success/reserve_200_1202_order-discount.json"));
        FILES.add(new Pair("/posresponse/__files/amc/reserve/amcReserveOrder404Response.json", "/amc/error/reserve_404_XXX_reserve-order.json"));
        FILES.add(new Pair("/posresponse/__files/amc/reserve/amcValidGAGetOrderResponse.json", "/amc/success/reserve_201_322_reques-reserve-order-ga.json"));
        FILES.add(new Pair("/posresponse/__files/amc/reserve/amcValidReservedGetOrderResponse.json", "/amc/success/reserve_200_XXX_get-order.json"));
        FILES.add(new Pair("/posresponse/__files/amc/seatmap/amcValidSeatmap.json", "/amc/success/seatmap_200_10200_get-seatmap.json"));
        FILES.add(new Pair("/posresponse/__files/amc/seatmap/amcValidSeatmapPremiere.json", "/amc/success/seatmap_200_10300_get-seatmap-loyalty-card.json"));
        FILES.add(new Pair("/posresponse/__files/amc/willcall/amcWillCall_bbb.json", "/amc/success/willcall_200_XXX_will-call-check-bbb.json"));
        FILES.add(new Pair("/posresponse/__files/amc/willcall/amcWillCall_bbc.json", "/amc/success/willcall_200_XXX_will-call-check-bbc.json"));
        FILES.add(new Pair("/posresponse/__files/amc/willcall/amcWillCall_bbd.json", "/amc/success/willcall_200_XXX_will-call-check-bbd.json"));
        FILES.add(new Pair("/posresponse/__files/amc/willcall/amcWillCall_bbe.json", "/amc/success/willcall_200_XXX_will-call-check-bbe.json"));
        FILES.add(new Pair("/posresponse/__files/amc/willcall/amcWillCall_d2e4.json", "/amc/success/willcall_200_XXX_will-call-check-d2e4.json"));
        FILES.add(new Pair("/posresponse/__files/amc/willcall/amcWillCall_d2e5.json", "/amc/success/willcall_200_XXX_will-call-check-d25e.json"));
        FILES.add(new Pair("/posresponse/__files/hybris/availability/reserved_available.json", "/hybris/success/availability_200_8796158640857_reserved.json"));
        FILES.add(new Pair("/posresponse/__files/hybris/availability/reserved_soldout.json", "/hybris/success/availability_200_8796158640859_soldout.json"));
        FILES.add(new Pair("/posresponse/__files/hybris/continue/error_failed_to_extend_order_does_not_exist.json", "/hybris/error/continue_404_3333333333_extend-order-not-exist.json"));
        FILES.add(new Pair("/posresponse/__files/hybris/reserve/response_order_id_1111111111.json", "/hybris/success/reserve_201_1111111111_order.json"));
        FILES.add(new Pair("/posresponse/__files/hybris/reserve/response_order_id_2222222222.json", "/hybris/success/reserve_201_2222222222_order.json"));
        FILES.add(new Pair("/posresponse/__files/hybris/reserve/response_order_id_3333333333.json", "/hybris/success/reserve_201_3333333333_order.json"));
        FILES.add(new Pair("/posresponse/__files/hybris/reserve/response_order_id_5555555555.json", "/hybris/success/reserve_201_5555555555_order.json"));
        FILES.add(new Pair("/posresponse/__files/penny/availability/GetAvailableSeatCountResponse.xml", "/penny/success/availability_200_XXX_seat-count.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/availability/GetAvailableSeatCountResponseSoldOut.xml", "/penny/success/availability_200_3201_seat-count-sold-out.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/cancel/ReleaseSeatsErrorResponse.xml", "/penny/error/cancel_200_1a3cd4f-3e6d8fff_release-seats.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/cancel/ReleaseSeatsSuccessResponse.xml", "/penny/success/cancel_200_1a3cd4f-3e6d8ff9_release-seats.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/cancel/ReleaseSeatsSuccessResponse.xml", "/penny/success/cancel_200_12345_release-seats.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/confirm/BuyProductResponse.xml", "/penny/success/confirm_200_XXX_buy.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/giftcard/GetGiftCardBalanceFailedResponse.xml", "/penny/error/gifcard_200_XXX_balance.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/giftcard/GetGiftCardBalanceSuccessResponse.xml", "/penny/success/gifcard_200_XXX_balance.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/reserve/HoldSeatsErrorResponse.xml", "/penny/error/reserve_200_XXX_hold-seats.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/reserve/HoldSeatsErrorResponse.xml", "/penny/error/reserve_200_XXX_hold-seats.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/reserve/HoldSeatsSuccessResponse.xml", "/penny/success/reserve_200_XXX_hold-seats.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/reserve/HoldSeatsSuccessResponse.xml", "/penny/success/reserve_200_XXX_hold-seats.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/reserve/HoldSeatsToBeCanceledResponse.xml", "/penny/success/reserve_200_XXX_hold-seats-tobe-canceled.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/seatmap/GetSeatMapResponse.xml", "/penny/success/seatmap_200_XXX.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/seatmap/GetUnavailableSeatsResponse.xml", "/penny/success/seatmap_200_XXX_unavailable-seats.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/tickets/GetTicketsFailedResponse.xml", "/penny/error/tickets_200_3201.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/tickets/GetTicketsSuccessResponse.xml", "/penny/success/tickets_200_3200.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/tickets/GetTicketsSuccessResponse.xml", "/penny/success/tickets_200_3203.xml"));
        FILES.add(new Pair("/posresponse/__files/penny/tickets/GetTicketsSuccessResponse.xml", "/penny/success/tickets_200_3202.xml"));
        FILES.add(new Pair("/posresponse/__files/retriever/availability/retrieverValidGAResponse.xml", "/retriver/success/availability_200_2201_valid-showtime.xml"));
        FILES.add(new Pair("/posresponse/__files/retriever/availability/retrieverValidReservedResponse.xml", "/retriver/success/availability_200_2200_seatmap.xml"));
        FILES.add(new Pair("/posresponse/__files/retriever/availability/retrieverValidSoldOutResponse.xml", "/retriver/success/availability_200_2400_valid-sold-out-showtime.xml"));
        FILES.add(new Pair("/posresponse/__files/retriever/cancel/RetrieverCancelReserveOrderSuccessResponse.xml", "/retriver/success/cancel_200_XXX_reserve-order.xml"));
        FILES.add(new Pair("/posresponse/__files/retriever/cancel/RetrieverCancelReserveOrderSuccessResponse.xml", "/retriver/success/cancel_200_XXX_retriever-transaction-guid.xml"));
        FILES.add(new Pair("/posresponse/__files/retriever/confirm/invalidConfirmOrderResponse.xml", "/retriver/error/confirm_400_XXX_invalid-order.xml"));
        FILES.add(new Pair("/posresponse/__files/retriever/confirm/validConfirmOrderResponse.xml", "/retriver/success/confirm_200_XXX_valid-order.xml"));
        FILES.add(new Pair("/posresponse/__files/retriever/continue/RetrieverContinueOrderErrorResponse.xml", "/retriver/error/continue_200_XXX_order.xml"));
        FILES.add(new Pair("/posresponse/__files/retriever/continue/RetrieverContinueOrderSuccessResponse.xml", "/retriver/success/continue_200_XXX_retriever-transaction-guid.xml"));
        FILES.add(new Pair("/posresponse/__files/retriever/continue/RetrieverContinueOrderSuccessResponse.xml", "/retriver/success/continue_200_XXX_order.xml"));
        FILES.add(new Pair("/posresponse/__files/retriever/reserve/retrieverGAReserveResponse2500.xml", "/retriver/success/reserve_200_2500_ga-order.xml"));
        FILES.add(new Pair("/posresponse/__files/retriever/reserve/retrieverInvalidGAGetOrderResponse.xml", "/retriver/error/reserve_400_XXX_create-new-order-buy-ga-tickets.xml"));
        FILES.add(new Pair("/posresponse/__files/retriever/reserve/retrieverOrderInfoResponse.xml", "/retriver/success/reserve_200_XXX_ga-order-details.xml"));
        FILES.add(new Pair("/posresponse/__files/retriever/reserve/retrieverReservedSeatingOrderInfoResponse.xml", "/retriver/success/reserve_200_XXX_order-details-reserved-seating.xml"));
        FILES.add(new Pair("/posresponse/__files/retriever/reserve/retrieverValidGAGetOrderResponse.xml", "/retriver/success/reserve_200_XXX_reserve-ga-order.xml"));
        FILES.add(new Pair("/posresponse/__files/retriever/reserve/retrieverValidReservedGetOrderResponse.xml", "/retriver/success/reserve_200_XXX_reserved-seat-order.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/availability/RtsCheckSoldOutGA.xml", "/rts/success/availability_200_123401_sold-out-ga.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/availability/RtsCheckSoldOutGASoldOut.xml", "/rts/success/availability_200_123402_sold-out_sold-out-ga.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/availability/RtsCheckSoldOutRS.xml", "/rts/success/availability_200_123501_sold-out-rs.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/availability/RtsCheckSoldOutRSSoldOut.xml", "/rts/success/availability_200_123502_sold-out-old-out-rs.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/confirm/RtsBuyErrorResponse.xml", "/rts/error/confirm_200_123501_buy.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/confirm/RtsBuySuccessResponse.xml", "/rts/success/confirm_200_123501_buy-reserved-seating.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/confirm/RtsBuySuccessResponse.xml", "/rts/success/confirm_200_123401_buy-general-admission.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/confirm/RtsBuySuccessResponseCheckRedeemRedeemed.xml", "/rts/success/confirm_200_XXX_redeemed.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/confirm/RtsReleaseSeatsSuccessResponse.xml", "/rts/error/confirm_200_123501_buy.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/reserve/RtsCheckSeatPicksErrorResponse.xml", "/rts/error/reserve_200_123502_check-seat-picks.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/reserve/RtsCheckSeatPicksSuccessResponse.xml", "/rts/success/reserve_200_123501_check-seat-picks.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/reserve/RtsHoldSeatsErrorResponse.xml", "/rts/error/reserve_200_123502_hold-seats.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/reserve/RtsHoldSeatsSuccessResponse.xml", "/rts/success/reserve_200_123501_hold-seats.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/seatmap/RtsSeatPlanRS.xml", "/rts/success/seatmap_200_123501_plan-rs.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/seatmap/RtsSeatPlanRSAllSeatTypes.xml", "/rts/success/seatmap_200_123503_plan-rs-all-seat-types.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/seatmap/RtsSeatPlanSoldOutRS.xml", "/rts/success/seatmap_200_123502_plan-sold-out-rs.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/inventoryCheck/RtsCheckRedeemNotFound.xml", "/rts/error/inventoryCheck_200_372730078811_redeem-not-found.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/inventoryCheck/RtsCheckRedeemRedeemed.xml", "/rts/success/inventoryCheck_200_372730078812_redeemed.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/inventoryCheck/RtsCheckRedeemSuccess.xml", "/rts/success/inventoryCheck_200_074727719392_redeem.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/inventoryReturn/RtsRefundNotFound.xml", "/rts/error/inventoryCheck_200_372730078811_not-found.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/inventoryReturn/RtsRefundRefunded.xml", "/rts/success/inventoryCheck_200_372730078812_refunded.xml"));
        FILES.add(new Pair("/posresponse/__files/rts/inventoryReturn/RtsRefundSuccess.xml", "/rts/success/inventoryCheck_200_074727719392_refund.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/availability/availability111111.xml", "/titan/success/availability_200_111111.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/availability/availability17008.xml", "/titan/success/availability_200_17008.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/availability/availability17009.xml", "/titan/success/availability_200_17009.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/availability/availability17010.xml", "/titan/success/availability_200_17010.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/availability/availability17011.xml", "/titan/success/availability_200_17011.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/availability/availability17012.xml", "/titan/success/availability_200_17012.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/availability/availability17013.xml", "/titan/success/availability_200_17013.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/availability/availability4626277.xml", "/titan/success/availability_200_4626277.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/cancel/cancel68381625.xml", "/titan/success/cancel_200_68381625.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/cancel/cancel99999999.xml", "/titan/success/cancel_200_99999999.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/cancel/cancelWithPayment68381625.xml", "/titan/success/cancel_200_68381625_reservation-shared-payment.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/cancel/cancelWithPayment99999999.xml", "/titan/error/cancel_200_99999999_reservation-shared-payment.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/cancel/errorResponse.xml", "/titan/error/cancel_200_error.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/cancel/successResponse.xml", "/titan/success/cancel_200_success.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/confirm/confirm17008.xml", "/titan/success/confirm_200_17008_reserved-seating.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/confirm/confirm17011.xml", "/titan/success/confirm_200_17011_general-admission.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/continue/errorResponse.xml", "/titan/error/continue_200_error.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/continue/successResponse.xml", "/titan/success/continue_200_success.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/eventData/event_data_111111.xml", "/titan/success/eventData_200_111111.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/eventData/event_data_17008.xml", "/titan/success/eventData_200_17008.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/eventData/event_data_17009.xml", "/titan/success/eventData_200_17009.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/eventData/event_data_17010.xml", "/titan/success/eventData_200_17010.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/eventData/event_data_17011.xml", "/titan/success/eventData_200_17011.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/eventData/event_data_4626277.xml", "/titan/success/eventData_200_4626277.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/getPosId/get_posId.xml", "/titan/success/confirm_200_17011_pos-id.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/reserve/errorResponse.xml", "/titan/error/reserve_200_error.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/reserve/successResponse.xml", "/titan/success/reserve_200_success.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/seatmap/getSeatPlanShared_section_1.xml", "/titan/success/seatmap_200_1_plan-shared.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/seatmap/getSeatPlanShared_section_2.xml", "/titan/success/seatmap_200_2_plan-shared.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/seatmap/getSeatPlanShared_section_3.xml", "/titan/success/seatmap_200_3_plan-shared.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/seatmap/getSeatPlanShared_section_4.xml", "/titan/success/seatmap_200_4_plan-shared.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/seatmap/getSeatPlanShared_section_5.xml", "/titan/success/seatmap_200_5_plan-shared.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/seatmap/getVenueSectionSeatsMin_section_1.xml", "/titan/success/seatmap_200_1_venue-section.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/seatmap/getVenueSectionSeatsMin_section_2.xml", "/titan/success/seatmap_200_2_venue-section.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/seatmap/getVenueSectionSeatsMin_section_3.xml", "/titan/success/seatmap_200_3_venue-section.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/seatmap/getVenueSectionSeatsMin_section_4.xml", "/titan/success/seatmap_200_4_venue-section.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/seatmap/getVenueSectionSeatsMin_section_5.xml", "/titan/success/seatmap_200_5_venue-section.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/return/TitanReturnErrorResponse.xml", "/titan/error/return_200_error.xml"));
        FILES.add(new Pair("/posresponse/__files/titan/return/TitanReturnSuccessResponse.xml", "/titan/success/return_200_success.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/authentication/VeeziAuthenticationResponse.xml", "/veezi/success/authentication_200_fandango-user.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/authentication/VeeziAuthenticationUser1Response.xml", "/veezi/success/authentication_200_fandango1_user.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/availability/VeeziGetNumSeatsLeftPerAreaNoSeatsAvailableResponse.xml", "/veezi/success/availability_200_XXX_get-num-seats-left-per-area-no-seats-available.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/availability/VeeziGetNumSeatsLeftPerAreaResponse.xml", "/veezi/success/availability_200_XXX_get-num-seats-left-per-area.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/availability/VeeziRegisterResponse.xml", "/veezi/success/availability_200_XXX_register.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/availability/VeeziRegisterUser1Response.xml", "/veezi/success/availability_200_XXX_register_user1.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/cancel/VeeziTransCancelResponse.xml", "/veezi/success/cancel_200_XXX_trans-cancel.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/confirm/VeeziTransCompleteResponse.xml", "/veezi/success/confirm_200_XXX_trans-complete.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/continue/VeeziTransContinueResponse.xml", "/veezi/success/continue_200_XXX_trans-continue.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/details/VeeziPaymentOkResponse.xml", "/veezi/success/details_200_XXX_payment-ok-ex.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/details/VeeziSetMemberInfoResponse.xml", "/veezi/success/details_200_XXX_set-member-info.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/reserve/VeeziGetPaymentTotalResponse.xml", "/veezi/success/reserve_200_XXX_get-payment-total.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/reserve/VeeziGetTransactionRefExResponse.xml", "/veezi/success/reserve_200_XXX_get-transaction-ref-ex.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/reserve/VeeziOrderTicketsResponse.xml", "/veezi/success/reserve_200_XXX_order-tickets.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/reserve/VeeziSetSelectedSeatsResponse.xml", "/veezi/success/reserve_200_XXX_set-selected-seats.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/reserve/VeeziStartPaymentResponse.xml", "/veezi/success/reserve_200_XXX_start-payment.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/reserve/VeeziTransNewResponse.xml", "/veezi/success/reserve_200_XXX_trans-new.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/seatmap/VeeziGetSessionSeatDataNoSeatsResponse.xml", "/veezi/success/seatmap_200_XXX_get-session-seat-data-no-seats.xml"));
        FILES.add(new Pair("/posresponse/__files/veezi/seatmap/VeeziGetSessionSeatDataResponse.xml", "/veezi/success/seatmap_200_XXX_get-session-seat-data.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/availability/VistaGAAvailable.xml", "/vista/success/availability_200_700100_get-num-seats-left-per-area.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/availability/VistaGANoData.xml", "/vista/success/availability_200_700105_no-data.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/availability/VistaGASoldout.xml", "/vista/success/availability_200_700101_general-admission-soldout.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/availability/VistaRSAvailable.xml", "/vista/success/availability_200_700102_reserved-seating-available.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/availability/VistaRSSoldout.xml", "/vista/success/availability_200_700103_reserved-seating-soldout.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/cancel/VistaTransCancelResponse.xml", "/vista/success/cancel_200_XXX_trans-cancel.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/confirm/VistaGetPaymentTotal.xml", "/vista/success/confirm_200_XXX_get-payment-total.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/confirm/VistaPaymentOkEx.xml", "/vista/success/confirm_200_XXX_payment-ok-ex.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/confirm/VistaPaymentRequest.xml", "/vista/success/confirm_200_XXX_payment-request.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/confirm/VistaPaymentStarting.xml", "/vista/success/confirm_200_XXX_payment-starting.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/confirm/VistaSetMemberInfo.xml", "/vista/success/confirm_200_XXX_set-member-info.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/confirm/VistaTransComplete.xml", "/vista/success/confirm_200_XXX_trans-complete.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/continue/VistaTransContinueResponse.xml", "/vista/success/continue_200_XXX_trans-continue.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/giftcard/VistaGiftCardPayment.xml", "/vista/success/gifcard_200_XXX_payment.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/giftcard/VistaGiftCardRefund.xml", "/vista/success/gifcard_200_XXX_refund.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/giftcard/VistaGiftCardStatus.xml", "/vista/success/gifcard_200_XXX_status.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/giftcard/VistaGiftCardStatusFailed.xml", "/vista/error/gifcard_200_XXX_status-failed.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/register/VistaRegisterResponse.xml", "/vista/success/register_200_XXX_register.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/reserve/VistaGetTransactionRefEx.xml", "/vista/success/reserve_200_XXX_get-transaction-ref-ex.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/reserve/VistaOrderTickets.xml", "/vista/success/reserve_200_XXX_order-tickets.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/reserve/VistaSetSelectedSeatsEx.xml", "/vista/success/reserve_200_700102_set-selected-seats-ex.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/reserve/VistaTransNew.xml", "/vista/success/reserve_200_XXX_trans_new.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/seatmap/VistaRSSeatmap.xml", "/vista/success/seatmap_200_700102_get-session-seatmap.xml"));
        FILES.add(new Pair("/posresponse/__files/vista/seatmap/VistaRSSeatmapSoldout.xml", "/vista/success/seatmap_200_700103_get-session-seatmap-soldout.xml"));
        FILES.add(new Pair("/posresponse/__files/radiant/saleCancelFull/saleCancelFullFailResponse.json", "/radiant/error/saleCancelFull_201_8030001.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/saleCancelFull/saleCancelFullSuccessResponse.json", "/radiant/success/saleCancelFull_201_803.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/confirm/confirmFailResponse.json", "/radiant/error/confirm_201_XXX_confirm-fail.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/confirm/confirmSuccessResponse.json", "/radiant/success/confirm_201_XXX_confirm-success.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/availability/availability37623.json", "/radiant/success/availability_201_37623.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/availability/availability37624.json", "/radiant/success/availability_201_37624.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/availability/availability37627.json", "/radiant/success/availability_201_37627.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/availability/availability37628.json", "/radiant/success/availability_201_37628.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/availability/invalidResponse37629.json", "/radiant/error/availability_201_37629_invalid.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/seatmap/available37627.json", "/radiant/success/seatmap_201_37627_available.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/seatmap/soldOutSold37628.json", "/radiant/success/seatmap_201_37628_sold-out-sold.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/seatmap/soldOutLocked37629.json", "/radiant/success/seatmap_201_37629_sold-out-locked.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/seatmap/soldOutBlocked37630.json", "/radiant/success/seatmap_201_37630_sold-out-blocked.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/seatmap/soldOutGAOverride37631.json", "/radiant/success/seatmap_201_37631_sold-out-ga-override.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/seatmap/mixAvailableNotAvailable37632.json", "/radiant/success/seatmap_201_37632_mix-available-not-available.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/seatmap/mixAvailableNotAvailableWheelChair37633.json", "/radiant/success/seatmap_201_37633_mix-available-not-available-wheel-chair.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/seatmap/invalidResponse37634.json", "/radiant/error/seatmap_201_37634_invalid.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/reserve/invalidResponse37626.json", "/radiant/error/reserve_201_37626_invalid.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/reserve/saleResponseAccepted37623.json", "/radiant/success/reserve_201_37623_accepted.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/reserve/saleResponseDeclined37624.json", "/radiant/error/reserve_201_37624_declined.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/reserve/saleResponseInvalid37625.json", "/radiant/error/reserve_201_37625_invalid.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/reserve/seatResponseAccepted37627.json", "/radiant/success/reserve_201_37627_accepted.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/reserve/seatResponseBusy37630.json", "/radiant/error/reserve_201_37630_busy.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/reserve/seatResponseDeclined37628.json", "/radiant/error/reserve_201_37628_declined.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/reserve/seatResponseError37631.json", "/radiant/error/reserve_201_37631_error.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/reserve/seatResponseInvalid37629.json", "/radiant/error/reserve_201_37629_invalid.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/reserve/seatResponseInvalidLength37634.json", "/radiant/error/reserve_201_37634_invalid-lenght.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/reserve/seatResponseUnmatched37632.json", "/radiant/error/reserve_201_37632_unmatched.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/return/inventoryReturnRefunded.json", "/radiant/error/return_400_0101969426_refunded.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/return/inventoryReturnSuccess.json", "/radiant/success/return_200_0101969425_refunded.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/return/inventoryStatusCheckNotFound.json", "/radiant/error/return_400_0101969424_not-found.json"));
        FILES.add(new Pair("/posresponse/__files/radiant/return/inventoryStatusCheckSuccess.json", "/radiant/success/return_200_0101969423_status.json"));

    }
}
