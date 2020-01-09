package io.github.luke_biel

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext

class IonCompletionProvider : CompletionProvider<CompletionParameters>() {
    companion object {
        @JvmField
        val INSTANCE = IonCompletionProvider()

        val CONFIG_SECTION = """
            [CONFIG] 
            | name | value | remark |
            |------|-------|--------|
            |      |       |        |
        """.trimIndent()

        val CONTRACT_SECTION = """
            [CONTRACT]
            id = "TEST_ID_1"
            currency = "EUR"
            hotel_code = "TEST_HOTEL"
            city = "TST"
            destinations = ["TST_DEST"]
            source = "TEST"
        """.trimIndent()

        val FULL_CONTRACT_SECTION = """
            [CONTRACT]
            id = "TEST_ID_1"
            name = "TEST_RESORT"
            currency = "EUR"
            active = true
            hotel_code = "TEST_HOTEL"
            city = "TST"
            booking = "20180101:20180131"
            markets_excluded = ["pl,de"]
            clients = ["@abc","!def"]
            brands = ["TEST"]
            destinations = ["TST_DEST"]
            source = "TEST"
            channel = "Test_channel"
            channel_hotel_code = "TEST_HOTEL_CH"
            scrape = true
            revision = 0
            type = "DS"
            tz = "Europe/Istanbul"
            missing_avl_eq_to_no_avl = true
            purchase_id = "TEST_PRCH_1"
            default_meal = "per_room"
            parent_id = "TEST_ID_0"
            product = "activity"
        """.trimIndent()

        val DEF_HOTEL_SECTION = """
            [DEF.HOTEL]
            | hotel_code | hotel_name | category | country | city | resort | destination_name | city_name | postal | street | lat | lon | json |
            |------------|------------|----------|---------|------|--------|------------------|-----------|--------|--------|-----|-----|------|
            |            |            |          |         |      |        |                  |           |        |        |     |     |      |
        """.trimIndent()

        val DEF_MEAL_SECTION = """
            [DEF.MEAL]
            | code | description | grp |
            |------|-------------|-----|
            |      |             |     |
        """.trimIndent()

        val DEF_ROOM_SECTION = """
            [DEF.ROOM]
            | code | description | occ | grp | properties | base_meal |
            |------|-------------|-----|-----|------------|-----------|
            |      |             |     |     |            |           |
        """.trimIndent()

        val RATE_PLAN_SECTION = """
            [RATE.PLAN]
            | dates | code | description | rooms | rules | props |
            |-------|------|-------------|-------|-------|-------|
            |       |      |             |       |       |       |
        """.trimIndent()

        val RATE_BASE_SECTION = """
            [RATE.BASE]
            | dates | charge | room | occ | meal | amount | rules | rp | id |
            |-------|--------|------|-----|------|--------|-------|----|----|
            |       |        |      |     |      |        |       |    |    |

        """.trimIndent()

        val RATE_RULE_SECTION = """
            [RATE.RULE]
            | dates | room | when | meal | rules | rp |
            |-------|------|------|------|-------|----|
            |       |      |      |      |       |    |
        """.trimIndent()

        val RATE_SUPPLEMENT_SECTION = """
            [RATE.SUPPLEMENT]
            | dates | rules | rate | comp | occ | kind | chg | calc | remark | rp | id |
            |-------|-------|------|------|-----|------|-----|------|--------|----|----|
            |       |       |      |      |     |      |     |      |        |    |    |
        """.trimIndent()

        val RATE_DISCOUNT_SECTION = """
            [RATE.DISCOUNT]
            | dates | rules | rate | comp | occ | applic | reduct | remark | rp | id |
            |-------|-------|------|------|-----|--------|--------|--------|----|----|
            |       |       |      |      |     |        |        |        |    |    |
        """.trimIndent()

        val RATE_DISCOUNT_GROUP_SECTION = """
            [RATE.DISCOUNT_GROUP]
            | combine |
            |---------|
            |         |
        """.trimIndent()

        val RATE_DISCOUNT_STRATEGY_SECTION = """
            [RATE.DISCOUNT_GROUP]
            | disc_id/group_id | group_id | strategy | name |
            |------------------|----------|----------|------|
            |                  |          |          |      |
        """.trimIndent()

        val RATE_CNX_SECTION = """
            [RATE.CNX]
            | dates | time | room | rules | rp | applic | calc | remark | id |
            |-------|------|------|-------|----|--------|------|--------|----|
            |       |      |      |       |    |        |      |        |    |
        """.trimIndent()

        val TAX_SECTION = """
            [TAX]
            | dates | rules | rate | component | occupancy | applicative | calc | remark | id |
            |-------|-------|------|-----------|-----------|-------------|------|--------|----|
            |       |       |      |           |           |             |      |        |    |
        """.trimIndent()

        val TAX_GROUP_SECTION = """
            [TAX_GROUP]
            | id |
            |----|
            |    |
        """.trimIndent()

        val SALES_PROFILE_SECTION = """
            [SALES.PROFILE]
            | name | clients | sales_cur | markets | avail | geo | hotel |
            |------|---------|-----------|---------|-------|-----|-------|
            |      |         |           |         |       |     |       |
        """.trimIndent()

        val SALES_MARKUP_SECTION = """
            [SALES.MARKUP]
            | dates | rules | rate | comp | markup | round | remark | rp | id | prio |
            |-------|-------|------|------|--------|-------|--------|----|----|------|
            |       |       |      |      |        |       |        |    |    |      |
        """.trimIndent()

        val RESTRICTION_SECTION = """
            [RESTRICTION]
            | rooms | occupancy | meal | rp | forbid | remark | id |
            |-------|-----------|------|----|--------|--------|----|
            |       |           |      |    |        |        |    |
        """.trimIndent()

        val QUERY_TRANSFORM_SECTION = """
            [QUERY.TRANSFORM]
            | occupancy | func | args | rate_rules |
            |-----------|------|------|------------|
            |           |      |      |            |
        """.trimIndent()

        val AVL_STATE_SECTION = """
            [AVL.STATE]
            | dates | room | rules | status | cnt | description |
            |-------|------|-------|--------|-----|-------------|
            |       |      |       |        |     |             |
        """.trimIndent()

        val AVL_INV_SECTION = """
            [AVL.INV]
            | dates | rules | room | allotments | from_room | from_rules |
            |-------|-------|------|------------|-----------|------------|
            |       |       |      |            |           |            |
        """.trimIndent()

        val RATE_SUPPLEMENT_CAT_SECTION = """
            [RATE.SUPPLEMENT_CAT]
            | cat | id | name |
            |-----|----|------|
            |     |    |      |
        """.trimIndent()

        val RATE_DISCOUNT_CAT_SECTION = """
            [RATE.DISCOUNT_CAT]
            | cat | id | name |
            |-----|----|------|
            |     |    |      |
        """.trimIndent()
    }

    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        result.addElement(createSectionLookupElement("config", CONFIG_SECTION))
        result.addElement(createSectionLookupElement("contract", CONTRACT_SECTION))
        result.addElement(createSectionLookupElement("contract_full", FULL_CONTRACT_SECTION))
        result.addElement(createSectionLookupElement("def_hotel", DEF_HOTEL_SECTION))
        result.addElement(createSectionLookupElement("def_meal", DEF_MEAL_SECTION))
        result.addElement(createSectionLookupElement("def_room", DEF_ROOM_SECTION))
        result.addElement(createSectionLookupElement("rate_plan", RATE_PLAN_SECTION))
        result.addElement(createSectionLookupElement("rate_base", RATE_BASE_SECTION))
        result.addElement(createSectionLookupElement("rate_rule", RATE_RULE_SECTION))
        result.addElement(createSectionLookupElement("rate_supplement", RATE_SUPPLEMENT_SECTION))
        result.addElement(createSectionLookupElement("rate_discount", RATE_DISCOUNT_SECTION))
        result.addElement(createSectionLookupElement("rate_discount_group", RATE_DISCOUNT_GROUP_SECTION))
        result.addElement(createSectionLookupElement("rate_discount_strategy", RATE_DISCOUNT_STRATEGY_SECTION))
        result.addElement(createSectionLookupElement("rate_cnx", RATE_CNX_SECTION))
        result.addElement(createSectionLookupElement("tax", TAX_SECTION))
        result.addElement(createSectionLookupElement("tax_group", TAX_GROUP_SECTION))
        result.addElement(createSectionLookupElement("sales_profile", SALES_PROFILE_SECTION))
        result.addElement(createSectionLookupElement("sales_markup", SALES_MARKUP_SECTION))
        result.addElement(createSectionLookupElement("restriction", RESTRICTION_SECTION))
        result.addElement(createSectionLookupElement("query_transform", QUERY_TRANSFORM_SECTION))
        result.addElement(createSectionLookupElement("avl_state", AVL_STATE_SECTION))
        result.addElement(createSectionLookupElement("avl_inv", AVL_INV_SECTION))
        result.addElement(createSectionLookupElement("rate_supplement_cat", RATE_SUPPLEMENT_CAT_SECTION))
        result.addElement(createSectionLookupElement("rate_discount_cat", RATE_DISCOUNT_CAT_SECTION))
    }

    private fun createSectionLookupElement(name: String, template: String): LookupElement {
        return LookupElementBuilder.create(template).withPresentableText(name).withLookupString(name).withTypeText("section")
    }
}
