package com.gustavocosme.atualizacao.altran.form

import java.util.*


class FormDocGenerate {
    private fun randomiza(n: Int): Int {
        return (Math.random() * n).toInt()
    }

    private fun mod(dividendo: Int, divisor: Int): Int {
        return Math.round(dividendo - Math.floor(dividendo / divisor.toDouble()) * divisor).toInt()
    }

    fun cpf(comPontos: Boolean): String {
        val n = 9
        val n1 = randomiza(n)
        val n2 = randomiza(n)
        val n3 = randomiza(n)
        val n4 = randomiza(n)
        val n5 = randomiza(n)
        val n6 = randomiza(n)
        val n7 = randomiza(n)
        val n8 = randomiza(n)
        val n9 = randomiza(n)
        var d1 =
            n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10
        d1 = 11 - mod(d1, 11)
        if (d1 >= 10) d1 = 0
        var d2 =
            d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11
        d2 = 11 - mod(d2, 11)
        var retorno: String? = null
        if (d2 >= 10) d2 = 0
        retorno = ""
        retorno =
            if (comPontos) "$n1$n2$n3.$n4$n5$n6.$n7$n8$n9-$d1$d2" else "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2
        return retorno
    }

    fun cnpj(comPontos: Boolean): String {
        val n = 9
        val n1 = randomiza(n)
        val n2 = randomiza(n)
        val n3 = randomiza(n)
        val n4 = randomiza(n)
        val n5 = randomiza(n)
        val n6 = randomiza(n)
        val n7 = randomiza(n)
        val n8 = randomiza(n)
        val n9 = 0 //randomiza(n);
        val n10 = 0 //randomiza(n);
        val n11 = 0 //randomiza(n);
        val n12 = 1 //randomiza(n);
        var d1 =
            n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8 + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4 + n1 * 5
        d1 = 11 - mod(d1, 11)
        if (d1 >= 10) d1 = 0
        var d2 =
            d1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3 + n3 * 4 + n2 * 5 + n1 * 6
        d2 = 11 - mod(d2, 11)
        if (d2 >= 10) d2 = 0
        var retorno: String? = null
        retorno =
            if (comPontos) "$n1$n2.$n3$n4$n5.$n6$n7$n8/$n9$n10$n11$n12-$d1$d2" else "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + n10 + n11 + n12 + d1 + d2
        return retorno
    }

    fun rg(comPontos: Boolean): String {
        var nDigResult: String
        val numerosContatenados: String
        var numeroGerado: String
        val numeroAleatorio = Random()
        //numeros gerados
        val n1 = numeroAleatorio.nextInt(10)
        val n2 = numeroAleatorio.nextInt(10)
        val n3 = numeroAleatorio.nextInt(10)
        val n4 = numeroAleatorio.nextInt(10)
        val n5 = numeroAleatorio.nextInt(10)
        val n6 = numeroAleatorio.nextInt(10)
        val n7 = numeroAleatorio.nextInt(10)
        val n8 = numeroAleatorio.nextInt(10)
        val n9 = numeroAleatorio.nextInt(10)
        //Conctenando os numeros
        numerosContatenados =
            n1.toString() + n2.toString() + n3.toString() + n4.toString() + n5.toString() + n6.toString() + n7.toString() + n8.toString() + n9.toString()
        numeroGerado = numerosContatenados
        numeroGerado =
            if (comPontos) "$n1$n2.$n3$n4$n5.$n6$n7$n8-$n9" else "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9
        return numeroGerado
    }

    fun isCPF(CPF: String): Boolean {
        var CPF = CPF
        CPF = removeCaracteresEspeciais(CPF)
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF == "00000000000" || CPF == "11111111111" || CPF == "22222222222" || CPF == "33333333333" || CPF == "44444444444" || CPF == "55555555555" || CPF == "66666666666" || CPF == "77777777777" || CPF == "88888888888" || CPF == "99999999999" || CPF.length != 11) return false
        val dig10: Char
        val dig11: Char
        var sm: Int
        var i: Int
        var r: Int
        var num: Int
        var peso: Int
        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        return try { // Calculo do 1o. Digito Verificador
            sm = 0
            peso = 10
            i = 0
            while (i < 9) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (CPF[i].toInt() - 48)
                sm = sm + num * peso
                peso = peso - 1
                i++
            }
            r = 11 - sm % 11
            dig10 =
                if (r == 10 || r == 11) '0' else (r + 48).toChar() // converte no respectivo caractere numerico
            // Calculo do 2o. Digito Verificador
            sm = 0
            peso = 11
            i = 0
            while (i < 10) {
                num = (CPF[i].toInt() - 48)
                sm = sm + num * peso
                peso = peso - 1
                i++
            }
            r = 11 - sm % 11
            dig11 = if (r == 10 || r == 11) '0' else (r + 48).toChar()
            // Verifica se os digitos calculados conferem com os digitos informados.
            if (dig10 == CPF[9] && dig11 == CPF[10]) true else false
        } catch (erro: InputMismatchException) {
            false
        }
    }

    fun isCNPJ(CNPJ: String): Boolean {
        var CNPJ = CNPJ
        CNPJ = removeCaracteresEspeciais(CNPJ)
        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ == "00000000000000" || CNPJ == "11111111111111" || CNPJ == "22222222222222" || CNPJ == "33333333333333" || CNPJ == "44444444444444" || CNPJ == "55555555555555" || CNPJ == "66666666666666" || CNPJ == "77777777777777" || CNPJ == "88888888888888" || CNPJ == "99999999999999" || CNPJ.length != 14) return false
        val dig13: Char
        val dig14: Char
        var sm: Int
        var i: Int
        var r: Int
        var num: Int
        var peso: Int
        // "try" - protege o código para eventuais erros de conversao de tipo (int)
        return try { // Calculo do 1o. Digito Verificador
            sm = 0
            peso = 2
            i = 11
            while (i >= 0) {
                // converte o i-ésimo caractere do CNPJ em um número:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posição de '0' na tabela ASCII)
                num = (CNPJ[i].toInt() - 48)
                sm = sm + num * peso
                peso = peso + 1
                if (peso == 10) peso = 2
                i--
            }
            r = sm % 11
            dig13 = if (r == 0 || r == 1) '0' else (11 - r + 48).toChar()
            // Calculo do 2o. Digito Verificador
            sm = 0
            peso = 2
            i = 12
            while (i >= 0) {
                num = (CNPJ[i].toInt() - 48)
                sm = sm + num * peso
                peso = peso + 1
                if (peso == 10) peso = 2
                i--
            }
            r = sm % 11
            dig14 = if (r == 0 || r == 1) '0' else (11 - r + 48).toChar()
            // Verifica se os dígitos calculados conferem com os dígitos informados.
            if (dig13 == CNPJ[12] && dig14 == CNPJ[13]) true else false
        } catch (erro: InputMismatchException) {
            false
        }
    }

    private fun removeCaracteresEspeciais(doc: String): String {
        var doc = doc
        if (doc.contains(".")) {
            doc = doc.replace(".", "")
        }
        if (doc.contains("-")) {
            doc = doc.replace("-", "")
        }
        if (doc.contains("/")) {
            doc = doc.replace("/", "")
        }
        return doc
    }


    public fun getName():String
    {

        val names       = listOf<String>("a","b","c","d","e","f","g","h","i","l","asddsfrwe","werew","weter","asdr","wewr","afet","ree");
        val total       = names.count()-1;
        val p1 = (0..total).random()
        val p2 = (0..total).random()
        val p3 = (0..total).random()

        return names[p1]+names[p2]+names[p3];

    }


}

