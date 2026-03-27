# Trabalho 1 - Problema 3

## Descrição

Sistema universal de controle de dispositivos IoT para automação residencial.
Controla persianas, lâmpadas e ar-condicionados de diferentes fabricantes de forma padronizada, utilizando o padrão **Adapter**.

Dispositivos suportados:
- **Lâmpadas**: ShoyuMi e Phellipes
- **Persianas**: Solarius e NatLight
- **Ar-condicionados**: VentoBaumn e GellaKaza

Funcionalidades:
- Controle universal de ligar/desligar, abrir/fechar, temperatura
- **Modo Sono**: desliga AC e luzes, fecha persianas
- **Modo Trabalho**: liga luzes e AC (temperatura 25), abre persianas

## Como executar

1. Coloque o arquivo `LibDispositivosIot1.0.jar` em `src/main/resources/`
2. Execute a classe `com.furb.app.Main`

## Estrutura

- `com.furb.adapter`: interfaces universais (`Lampada`, `Persiana`, `ArCondicionado`) e adapters para cada dispositivo.
- `com.furb.facade`: fachada `ControladorCasa` com controle centralizado e modos.
- `com.furb.app`: ponto de entrada.
- `src/test/java`: testes unitários com JUnit 5.

## Diagrama de classes (UML)

```mermaid
classDiagram
    class Lampada {
        <<interface>>
        +ligar() void
        +desligar() void
        +estaLigada() boolean
    }

    class Persiana {
        <<interface>>
        +abrir() void
        +fechar() void
        +estaAberta() boolean
    }

    class ArCondicionado {
        <<interface>>
        +ligar() void
        +desligar() void
        +estaLigado() boolean
        +aumentarTemperatura() void
        +diminuirTemperatura() void
        +definirTemperatura(int temperatura) void
        +getTemperatura() int
    }

    class LampadaShoyuMiAdapter {
        -LampadaShoyuMi lampada
        +ligar() void
        +desligar() void
        +estaLigada() boolean
    }

    class LampadaPhellipesAdapter {
        -LampadaPhellipes lampada
        +ligar() void
        +desligar() void
        +estaLigada() boolean
    }

    class PersianaSolariusAdapter {
        -PersianaSolarius persiana
        +abrir() void
        +fechar() void
        +estaAberta() boolean
    }

    class PersianaNatLightAdapter {
        -PersianaNatLight persiana
        +abrir() void
        +fechar() void
        +estaAberta() boolean
    }

    class ArCondicionadoVentoBaumnAdapter {
        -ArCondicionadoVentoBaumn ac
        +ligar() void
        +desligar() void
        +estaLigado() boolean
        +aumentarTemperatura() void
        +diminuirTemperatura() void
        +definirTemperatura(int temperatura) void
        +getTemperatura() int
    }

    class ArCondicionadoGellaKazaAdapter {
        -ArCondicionadoGellaKaza ac
        +ligar() void
        +desligar() void
        +estaLigado() boolean
        +aumentarTemperatura() void
        +diminuirTemperatura() void
        +definirTemperatura(int temperatura) void
        +getTemperatura() int
    }

    class ControladorCasa {
        -List~Lampada~ lampadas
        -List~Persiana~ persianas
        -List~ArCondicionado~ arCondicionados
        +addLampada(Lampada lampada) void
        +addPersiana(Persiana persiana) void
        +addArCondicionado(ArCondicionado ac) void
        +ligarLampadas() void
        +desligarLampadas() void
        +abrirPersianas() void
        +fecharPersianas() void
        +ligarArCondicionados() void
        +desligarArCondicionados() void
        +aumentarTemperatura() void
        +diminuirTemperatura() void
        +definirTemperatura(int temperatura) void
        +modoSono() void
        +modoTrabalho() void
        +getLampadas() List~Lampada~
        +getPersianas() List~Persiana~
        +getArCondicionados() List~ArCondicionado~
    }

    class LampadaShoyuMi {
        -boolean ligada
        +ligar() void
        +desligar() void
        +estaLigada() boolean
    }

    class LampadaPhellipes {
        -int intensidade
        +setIntensidade(int intensidade) void
        +getIntensidade() int
    }

    class PersianaSolarius {
        -boolean aberta
        +subirPersiana() void
        +descerPersiana() void
        +estaAberta() boolean
    }

    class PersianaNatLight {
        -boolean palhetaAberta
        -boolean palhetaErguida
        +descerPalheta() void
        +subirPalheta() void
        +abrirPalheta() void
        +fecharPalheta() void
        +estaPalhetaAberta() boolean
        +estaPalhetaErguida() boolean
    }

    class ArCondicionadoVentoBaumn {
        -int temperatura
        -boolean ligado
        +ligar() void
        +desligar() void
        +definirTemperatura(int temperatura) void
        +estaLigado() boolean
        +getTemperatura() int
    }

    class ArCondicionadoGellaKaza {
        -int temperatura
        -boolean ligado
        +ativar() void
        +desativar() void
        +aumentarTemperatura() void
        +diminuirTemperatura() void
        +estaLigado() boolean
        +getTemperatura() int
    }

    Lampada <|.. LampadaShoyuMiAdapter
    Lampada <|.. LampadaPhellipesAdapter
    Persiana <|.. PersianaSolariusAdapter
    Persiana <|.. PersianaNatLightAdapter
    ArCondicionado <|.. ArCondicionadoVentoBaumnAdapter
    ArCondicionado <|.. ArCondicionadoGellaKazaAdapter

    LampadaShoyuMiAdapter --> LampadaShoyuMi
    LampadaPhellipesAdapter --> LampadaPhellipes
    PersianaSolariusAdapter --> PersianaSolarius
    PersianaNatLightAdapter --> PersianaNatLight
    ArCondicionadoVentoBaumnAdapter --> ArCondicionadoVentoBaumn
    ArCondicionadoGellaKazaAdapter --> ArCondicionadoGellaKaza

    ControladorCasa --> Lampada
    ControladorCasa --> Persiana
    ControladorCasa --> ArCondicionado
```

## Testes

Os testes estão em `trabalho3/src/test/java`.
