# Implémentation d'un Réseau de Neurones avec Design Patterns

## Table des Matières

1. [Introduction](https://www.notion.so/Exam-Design-Patterns-Ali-Ben-hammad-16c66252d6ea8081b70aecc810702d31?pvs=21)
2. [Architecture du Projet](https://www.notion.so/Exam-Design-Patterns-Ali-Ben-hammad-16c66252d6ea8081b70aecc810702d31?pvs=21)
3. [Design Patterns Utilisés](https://www.notion.so/Exam-Design-Patterns-Ali-Ben-hammad-16c66252d6ea8081b70aecc810702d31?pvs=21)
4. [Implémentation](https://www.notion.so/Exam-Design-Patterns-Ali-Ben-hammad-16c66252d6ea8081b70aecc810702d31?pvs=21)
5. [Aspects (AOP)](https://www.notion.so/Exam-Design-Patterns-Ali-Ben-hammad-16c66252d6ea8081b70aecc810702d31?pvs=21)
6. [Exécution et Tests](https://www.notion.so/Exam-Design-Patterns-Ali-Ben-hammad-16c66252d6ea8081b70aecc810702d31?pvs=21)
7. [Conclusion](https://www.notion.so/Exam-Design-Patterns-Ali-Ben-hammad-16c66252d6ea8081b70aecc810702d31?pvs=21)

## Introduction

Ce projet implémente un réseau de neurones artificiels en utilisant Java et plusieurs design patterns. L'objectif principal est de démontrer l'utilisation efficace des patterns de conception et des principes de programmation orientée aspect (AOP) plutôt que de se concentrer sur les algorithmes de deep learning.

## Architecture du Projet

### Structure du Projet

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/7efb5cee-835a-46c2-a61f-58ed4cbe0c9c/25626f76-1af9-4e84-9b6d-ef25b9fd8476/image.png)

### Diagramme de Classes

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/7efb5cee-835a-46c2-a61f-58ed4cbe0c9c/5e67c022-4aed-4772-a668-aa479a1697c0/image.png)

Le projet est organisé en plusieurs packages :

- `activations` : Contient les fonctions d'activation
- `aspects` : Contient les aspects de logging et de sécurité
- `networkState` : Gère les états du réseau
- `observer` : Implémente le pattern Observer
- Classes principales du réseau de neurones

## Design Patterns Utilisés

### 1. Pattern Strategy 🎯

Utilisé pour les fonctions d'activation.

### Objectif

Permettre l'interchangeabilité des fonctions d'activation.

### Implémentation

```java
public interface ActivationFunction {
     double activate(double input);
}

public class Identity implements ActivationFunction {
    @Override
    public double activate(double input) {
        return input;
    }
}

public class Sigmoid implements ActivationFunction {
    @Override
    public double activate(double input) {
        return 1 / (1 + Math.exp(-input));
    }
}
```

### 2. Pattern Adapter 🔄

Utilisé pour intégrer d'anciennes fonctions d'activation.

### Objectif

Adapter une interface existante à celle attendue par le système.

### Implémentation

```java
public interface LegacyActivation {
    double compute(double value);
}

public class OldActivationFunction implements LegacyActivation {
    @Override
    public double compute(double value) {
        return Math.tanh(value);
    }
}

public class ActivationAdapter implements ActivationFunction {
    private LegacyActivation legacyFunction;

    public ActivationAdapter(LegacyActivation legacyFunction) {
        this.legacyFunction = legacyFunction;
    }

    @Override
    public double activate(double input) {
        return legacyFunction.compute(input);
    }
}
```

### 3. Pattern State 📊

Gère les différents états du réseau de neurones.

### Objectif

Contrôler le comportement du réseau selon son état.

### Implémentation

```java
public interface NetworkState {
    void train(NeuralNetwork network);
    void fit(NeuralNetwork network);
    void predict(NeuralNetwork network, double[] input);
}

public class ConstructionState implements NetworkState {
    @Override
    public void train(NeuralNetwork network) {
        network.setState(new TrainingState());
        System.out.println("Starting training...");
    }

    @Override
    public void fit(NeuralNetwork network) {
        network.setState(new TrainedState());
        System.out.println("Direct fit to trained state");
    }

    @Override
    public void predict(NeuralNetwork network, double[] input) {
        throw new IllegalStateException("Cannot predict in construction state");
    }
}

public
class TrainedState implements NetworkState {
    @Override
    public void train(NeuralNetwork network) {
        System.out.println("Already trained");
    }

    @Override
    public void fit(NeuralNetwork network) {
        throw new IllegalStateException("Already trained");
    }

    @Override
    public void predict(NeuralNetwork network, double[] input) {
        // Actual prediction logic would go here
        System.out.println("Predicting...");
    }
}

public class TrainingState implements NetworkState {
    @Override
    public void train(NeuralNetwork network) {
        System.out.println("Already training");
    }

    @Override
    public void fit(NeuralNetwork network) {
        throw new IllegalStateException("Cannot fit while training");
    }

    @Override
    public void predict(NeuralNetwork network, double[] input) {
        throw new IllegalStateException("Cannot predict while training");
    }

    public void completeTraining(NeuralNetwork network) {
        network.setState(new TrainedState());
    }
}
```

### 4. Pattern Observer 👀

Utilisé pour notifier le tableau de bord des changements d'état.

### Objectif

Maintenir une liaison faible entre le réseau et ses observateurs.

### Implémentation

```java
public interface NetworkObserver {
    void update(String state, double accuracy);
}

public class Dashboard implements NetworkObserver {
    @Override
    public void update(String state, double accuracy) {
        System.out.println("Dashboard updated - State: " + state + ", Accuracy: " + accuracy);
    }
}

public class NeuralNetwork {
   private List<NetworkObserver> observers = new ArrayList<>();
   
   public void addObserver(NetworkObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        double mockAccuracy = 0.95; // Mock accuracy for demonstration
        for (NetworkObserver observer : observers) {
            observer.update(state.getClass().getSimpleName(), mockAccuracy);
        }
    }

```

### 5. Pattern Builder 🏗️

Utilisé pour la construction du réseau de neurones.

### Objectif

Simplifier la création d'instances complexes.

### Implémentation

```java
   public static class Builder {
        private NeuralNetwork network;

        public Builder() {
            network = new NeuralNetwork();
        }

        public Builder addLayer(Layer layer) {
            network.layers.add(layer);
            return this;
        }

        public NeuralNetwork build() {
            if (network.layers.size() < 3) {
                throw new IllegalStateException("Network must have at least 3 layers");
            }
            return network;
        }
    }
```

## Aspects (AOP)

### Aspect de Logging 📝

```java
@Aspect
@Component
public class LoggingAspect {
    @Around("execution(* enset.ali..*.*(..))")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("📝 Executing: " + joinPoint.getSignature().getDeclaringType().getSimpleName()
            + "." + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        System.out.println("✅ Completed: " + joinPoint.getSignature().getDeclaringType().getSimpleName()
            + "." + joinPoint.getSignature().getName());
        return result;
    }
}
```

### Fonctionnalités

- Journalisation des appels de méthodes
- Mesure du temps d'exécution
- Traçage des entrées/sorties

### Aspect de Sécurité 🔒

```java
@Aspect
@Component
public class SecurityAspect {
    private static final String ADMIN_PASSWORD = "admin";
    private boolean isAuthenticated = false;
    private Scanner scanner = new Scanner(System.in);

    @Before("@annotation(RequiresAuthentication)")
    public void checkSecurity(JoinPoint joinPoint) {
        if (!isAuthenticated) {
            System.out.print("🔐 Please enter password to execute the application: ");
            String password = scanner.nextLine();

            if (!ADMIN_PASSWORD.equals(password)) {
                throw new SecurityException("❌ Invalid password. Access denied.");
            }
            isAuthenticated = true;
            System.out.println("✅ Access granted. Executing application...\n");
        }
    }
}
```

### Fonctionnalités

- Authentification par mot de passe
- Contrôle d'accès aux méthodes sensibles
- Journalisation des tentatives d'accès

## Exécution et Tests

### Tests des Design Patterns

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/7efb5cee-835a-46c2-a61f-58ed4cbe0c9c/5854c3d4-69f5-420b-af7b-ac2eea44fa54/image.png)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/7efb5cee-835a-46c2-a61f-58ed4cbe0c9c/54ac5a71-c3dc-4c0c-89da-8fae76ace082/image.png)

## Conclusion

### Points Forts

- Architecture modulaire et extensible
- Séparation claire des responsabilités
- Facilité d'ajout de nouvelles fonctionnalités

### Améliorations Possibles

1. Ajout de nouveaux types de couches
2. Implémentation de nouvelles fonctions d'activation
3. Amélioration du système de logging
4. Extension des fonctionnalités du tableau de bord

### Apprentissages

- Application pratique des design patterns
- Utilisation de l'AOP dans un contexte réel
- Gestion de la complexité dans un système orienté objet

---

Ali ben-hammad GLSID3
