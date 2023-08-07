package com.luiz.moco.doterra;

import java.text.NumberFormat;

public enum ProdutoEnum {
    // Óleos Essenciais
    ARBORVITAE("60203804", "Arborvitae - Arborvitae 5 ml", 193.00, 145.00, 25),
    BASIL("60203876", "Basil - Manjericão 15 ml", 245.00, 184.00, 23),
    BERGAMOT("60203875", "Bergamot - Bergamota 15 ml", 239.00, 179.00, 31),
    BLACK_PEPPER("60206619", "Black Pepper - Pimenta-preta 5 ml", 188.00, 141.00, 25),
    BLACK_SPRUCE("60210031", "Black Spruce - Abeto-negro 5 ml", 132.00, 99.00, 18),
    BLUE_TANSY("60211785", "Blue Tansy - Tanaceto-azul 5 ml", 532.00, 399.00, 72),
    BREU_BRANCO("60217234", "Breu Branco 5ml", 260.00, 195.00, 32),
    CARDAMOM("60203805", "Cardamom - Cardamomo 5 ml", 217.00, 163.00, 29),
    CASSIA("60203874", "Cassia - Canela-cássia 15 ml", 148.00, 111.00, 20),
    CEDARWOOD("60203416", "Cedarwood - Cedro 15 ml", 109.00, 82.00, 14),
    CELERY_SEED("60210086", "Celery Seed - Semente de Aipo 15 ml", 255.00, 191.00, 31),
    CILANTRO("60203873", "Cilantro - Coentro 15 ml", 220.00, 165.00, 29),
    CITRONELLA("60210368", "Citronella - Citronela 15 ml", 99.00, 74.00, 12),
    CLARY_SAGE("60203872", "Clary Sage - Sálvia-esclareia 15 ml", 316.00, 237.00, 41),
    CLOVE("60203871", "Clove - Cravo 15 ml", 129.00, 97.00, 16),
    COPAIBA("60203605", "Copaiba - Copaíba 15 ml", 287.00, 215.00, 40),
    CORIANDER("60203870", "Coriander - Coentro (Sementes) 15 ml", 167.00, 125.00, 23),
    CYPRESS("60203418", "Cypress - Cipreste 15 ml", 141.00, 106.00, 18),
    DOUGLAS_FIR("60203851", "Douglas Fir - Abeto de Douglas 5 ml", 179.00, 134.00, 22),
    EUCALYPTUS("60203326", "Eucalyptus - Eucalipto 15 ml", 125.00, 94.00, 16),
    FENNEL("60203788", "Fennel (Sweet) - Erva-doce 15 ml", 109.00, 82.00, 14),
    FRANKINCENSE("60203411", "Frankincense - Olíbano 15 ml", 519.00, 389.00, 64),
    GERANIUM("60203787", "Geranium - Gerânio 15 ml", 255.00, 191.00, 31),
    GINGER("60203928", "Ginger - Gengibre 15 ml", 287.00, 215.00, 33),
    GRAPEFRUIT("60203423", "Grapefruit - Toranja 15 ml", 155.00, 116.00, 17),
    GREEN_MANDARIN("60208446", "Green Mandarin - Mandarina-verde 15 ml", 219.00, 164.00, 28),
    HAWAIIAN_SANDALWOOD("60205862", "Hawaiian Sandalwood - Sândalo-havaiano 5 ml", 567.00, 425.00, 60),
    HELICHRYSUM("60203810", "Helichrysum - Sempre-viva 5 ml", 651.00, 488.00, 65),
    JUNIPER_BERRY("60203921", "Juniper Berry - Zimbro 5 ml", 157.00, 118.00, 21),
    LAVENDER("60203327", "Lavender - Lavanda 15 ml", 197.00, 148.00, 23),
    LEMON("60203419", "Lemon - Limão-siciliano 15 ml", 92.00, 69.00, 11),
    LEMON_EUCALYPTUS("60210370", "Lemon Eucalyptus - Eucalipto-limão 15 ml", 89.00, 67.00, 12),
    LEMONGRASS("60203322", "Lemongrass - Capim-limão 15 ml", 85.00, 64.00, 11),
    LIME("60203420", "Lime - Limão 15 ml", 115.00, 86.00, 14),
    MADAGASCAR_VANILLA("60219031", "Madagascar Vanilla - Baunilha de Madagascar 5ml", 276.00, 207.00, 34),
    MARJORAM("60203329", "Marjoram - Manjerona 15 ml", 171.00, 128.00, 21),
    MELALEUCA("60203421", "Melaleuca - Tea Tree 15 ml", 163.00, 122.00, 21),
    MELISSA("60214171", "Melissa 5 ml", 785.00, 589.00, 76),
    MYRRH("60203924", "Myrrh - Mirra 15 ml", 524.00, 393.00, 51),
    OREGANO("60203412", "Oregano - Orégano 15 ml", 175.00, 131.00, 23),
    PATCHOULI("60203923", "Patchouli - Patchouli 15 ml", 235.00, 176.00, 29),
    PEPPERMINT("60203323", "Peppermint - Hortelã-pimenta 15 ml", 177.00, 133.00, 23),
    PETITGRAIN("60203936", "Petitgrain - Petitgrain 15 ml", 183.00, 137.00, 23),
    ROMAN_CHAMOMILE("60203920", "Roman Chamomile - Camomila-romana 5 ml", 405.00, 304.00, 51),
    ROSEMARY("60203413", "Rosemary - Alecrim 15 ml", 132.00, 99.00, 16),
    SIBERIAN_FIR("60203324", "Siberian Fir - Pinheiro-siberiano 15 ml", 136.00, 102.00, 19),
    SPEARMINT("60206600", "Spearmint - Hortelã-verde 15 ml", 211.00, 158.00, 29),
    SPIKENARD("60205841", "Spikenard - Nardo 5 ml", 447.00, 335.00, 56),
    TANGERINE("60222819", "Tangerine - Tangerina 15 ml", 124.00, 93.00, 17),
    THYME("60203933", "Thyme - Tomilho 15 ml", 233.00, 175.00, 28),
    VETIVER("60203422", "Vetiver - Vetiver 15 ml", 409.00, 307.00, 39),
    WILD_ORANGE("60203415", "Wild Orange - Laranja-selvagem 15 ml", 89.00, 67.00, 12),
    WINTERGREEN("60203932", "Wintergreen - Wintergreen 15 ml", 143.00, 107.00, 18),
    YLANG_YLANG("60203414", "Ylang Ylang - Ylang Ylang 15 ml", 299.00, 224.00, 37),
    ADAPTIV("60210367", "Adaptiv® 15 ml", 303.00, 227.00, 37),
    AIR_X("60217400", "Air-X™ 15 ml", 185.00, 139.00, 23),
    AROMATOUCH("60203802", "dōTERRA AromaTouch® 15 ml", 232.00, 174.00, 29),
    BALANCE("60203389", "dōTERRA Balance® 15 ml", 156.00, 117.00, 21),
    BREATHE("60203392", "dōTERRA Breathe® 15 ml", 183.00, 137.00, 23),
    CITRUS_BLISS("60203417", "Citrus Bliss® 15 ml", 144.00, 108.00, 17),
    DDR_PRIME("60209540", "DDR Prime® 15 ml", 240.00, 180.00, 29),
    DEEP_BLUE("60203394", "dōTERRA Deep Blue® 5 ml", 252.00, 189.00, 36),
    ELEVATION("60203789", "dōTERRA Elevation® 15 ml", 420.00, 315.00, 42),
    ON_GUARD("60203410", "On Guard® 15 ml", 259.00, 194.00, 36),
    PURIFY("60203424", "dōTERRA Purify® 15 ml", 151.00, 113.00, 20),
    SERENITY("60203325", "dōTERRA Serenity™ 15 ml", 265.00, 199.00, 34),
    SMART_SASSY("60203935", "dōTERRA Smart & Sassy® 15 ml", 220.00, 165.00, 28),
    TERRASHIELD("60216342", "TerraShield® 15 ml", 87.00, 65.00, 11),
    WHISPER("60215129", "Whisper™ Touch - Roll-On 5 ml", 119.00, 89.00, 15),
    YARROW_POM("60209636", "Yarrow|Pom 15 ml", 360.00, 270.00, 47),
    ZENDOCRINE("60203931", "Zendocrine® 15 ml", 197.00, 148.00, 24),
    ZENGEST("60214934", "ZenGest® 15 ml", 257.00, 193.00, 35),

    // Produtos para o corpo
    HD_CLEAR_ROLL_ON("60206659", "HD Clear - Roll-On 10 ml", 143.00, 107.00, 18),
    YARROW_POM_DUO("60212418", "Yarrow | Pom - Ativo Botânico / Nutritivo Duo 30 ml", 728.00, 546.00, 93),
    YARROW_POM_SERUM("60211397", "Yarrow | Pom - Sérum Firmador Corporal 100 ml", 553.00, 415.00, 60),

    // Produtos para a casa
    DOTERRA_BRASIL_LIVING_KIT("60215422", "dōTERRA® Brasil Living Kit Caixa", 1007.00, 755.00, 120),
    DOTERRA_DAWN_UMIDIFICADOR("60220484", "dōTERRA Dawn® Umidificador Aromático Unidade", 729.00, 547.00, 15),
    DOTERRA_PETAL_DIFUSOR("60215423", "Difusor Petal 2.0 com dois óleos (Wild Orange 5 ml e Lavender 5 ml) Unidade", 532.00, 399.00, 20),
    DOTERRA_VOLO_MARMO("60211975", "Difusor dōTERRA Volo™ - Mármore Unidade", 567.00, 425.00, 15),
    DOTERRA_VOLO_ONIX("60211974", "Difusor dōTERRA Volo™ - Ônix Unidade", 567.00, 425.00, 15),

    // Kits
    DOTERRA_CULINARIA_ESSENCIAL("60215466", "dōTERRA® Culinária Essencial Caixa com Livro de Receitas", 713.00, 535.00, 65),
    DOTERRA_CULINARIA_ESSENCIAL_SEM_LIVRO("60219174", "dōTERRA® Culinária Essencial Sem Livro de Receitas Caixa", 573.00, 430.00, 55),
    LIVRO_CULINARIA_ESSENCIAL("60217040", "Livro Culinária Essencial Livro de Receitas", 120.00, 90.00, 10),
    DOTERRA_KIT_APRESENTACAO("60215282", "dōTERRA® Kit de Apresentação Caixa", 149.00, 112.00, 13),
    DOTERRA_AROMATOUCH("60215361", "Kit Técnica dōTERRA AromaTouch® Caixa", 873.00, 655.00, 104),
    DOTERRA_AROMATOUCH_COM_DIFUSOR("60216401", "dōTERRA® Kit Técnica doTERRA® AromaTouch® com Difusor Caixa com Difusor", 1307.00, 980.00, 115),
    KIT_CUIDADOS_PELE_VERAGE("60204533", "Kit de Cuidados para a Pele Veráge® Caixa", 731.00, 548.00, 67),
    DOTERRA_KIDS_KIT("60209805", "dōTERRA® Kids Kit Unidade", 817.00, 613.00, 89),
    DOTERRA_ESSENCIAL_PARA_LAR("60225644", "dōTERRA® Essencial Para o Lar Caixa com Difusor", 2187.00, 1640.00, 200),
    DOTERRA_SOLUCOES_NATURAIS("60215467", "dōTERRA® Soluções Naturais Caixa com Difusor e dōTERRA® Wooden Box", 3987.00, 2990.00, 400),
    DOTERRA_COLECIONADOR("60215470", "dōTERRA® Colecionador Coleção com 69 itens", 7993.00, 5995.00, 800),
    DOTERRA_DIAMOND("60218036", "dōTERRA® Diamond Coleção com 107 itens", 15993.00, 11995.00, 1600),

    // Materiais e acessórios
    DOTERRA_WOODEN_BOX("60207429", "dōTERRA® Wooden Box Unidade", 105.00, 79.00, 0),
    CAP_STICKERS("60206688", "Cap Stickers - Adesivos para tampa de Óleos Essenciais Unidade", 8.00, 6.00, 0),
    FORMULARIO_CADASTRO_MEMBROS("60216404", "Formulário de Cadastro de Membros Bloco com 50 unidades", 15.00, 11.00, 0),
    OLEOS_NATURAIS_PARA_CASA("60216403", "Óleos Naturais Para a sua Casa Bloco com 50 unidades", 15.00, 11.00, 0),
    LISTA_PRECOS_KITS("60216402", "Lista de Preços com Opções de Kits Bloco com 50 unidades", 15.00, 11.00, 0),
    CLASS_IN_A_BOX("60219241", "Class in a Box Unidade", 127.00, 95.00, 0),
    GUIA_PRODUTOS_A5("60206980", "Guia de Produtos A5 Pacote com 10 Unidades", 15.00, 11.00, 0),
    REVISTA_DOTERRA("60216008", "Revista dōTERRA® Guia de Produtos Unidade", 19.00, 14.00, 0),
    GUIA_SOLUCOES_NATURAIS("60207451", "Guia Soluções Naturais Pacote com 10 Unidades", 20.00, 15.00, 0),
    GUIA_VIVA("60208518", "Guia Viva Pacote com 10 Unidades", 20.00, 15.00, 0),
    GUIA_COMPARTILHE("60208543", "Guia Compartilhe Pacote com 10 Unidades", 15.00, 11.00, 0),
    GUIA_CONSTRUA("60208519", "Guia Construa Pacote com 10 Unidades", 15.00, 11.00, 0),
    GUIA_DECOLE("60207452", "Guia Decole Pacote com 10 Unidades", 31.00, 23.00, 0),
    GUIA_TREINE("60210129", "Guia Treine Pacote com 10 Unidades", 20.00, 15.00, 0),
    GUIA_LIDERE("60210128", "Guia Lidere Pacote com 10 Unidades", 20.00, 15.00, 0),
    GUIA_MULTIPLIQUE("60211687", "Guia Multiplique Unidade", 7.00, 5.00, 0);

    private final String codigo;
    private final String descricao;
    private final double precoVarejo;
    private final double precoAtacado;
    private final int pontoVolume;

    ProdutoEnum(String codigo, String descricao, double precoVarejo, double precoAtacado, int pontoVolume) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.precoVarejo = precoVarejo;
        this.precoAtacado = precoAtacado;
        this.pontoVolume = pontoVolume;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPrecoVarejo() {
        return precoVarejo;
    }

    public double getPrecoAtacado() {
        return precoAtacado;
    }

    public int getPontoVolume() {
        return pontoVolume;
    }

    // Método para buscar um produto pelo código
    public static ProdutoEnum getByDescricao(String descricao) {
        for (ProdutoEnum produto : ProdutoEnum.values()) {
            if ((produto.getDescricao() + " - " +
                    NumberFormat.getCurrencyInstance().format(produto.getPrecoVarejo())).equalsIgnoreCase(String.valueOf(descricao))) {
                return produto;
            }
        }
        return null; // Retorna null caso não encontre o produto com o código especificado
    }

    public static double getMenorValorPorPonto() {
        double menor = 50;
        for (ProdutoEnum produto : ProdutoEnum.values()) {
            if (produto.getPontoVolume() != 0) {
                if ((produto.getPrecoAtacado() / produto.getPontoVolume()) < menor) {
                    menor = (produto.getPrecoAtacado() / produto.getPontoVolume());
                }
            }
        }
        return menor; // Retorna null caso não encontre o produto com o código especificado
    }

    public static double getMaiorValorPorPonto() {
        double maior = 0;
        for (ProdutoEnum produto : ProdutoEnum.values()) {
            if (produto.getPontoVolume() != 0) {
                if ((produto.getPrecoAtacado() / produto.getPontoVolume()) > maior) {
                    maior = (produto.getPrecoAtacado() / produto.getPontoVolume());
                }
            }
        }
        return maior; // Retorna null caso não encontre o produto com o código especificado
    }
}
