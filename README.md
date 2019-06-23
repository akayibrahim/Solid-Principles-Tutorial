# SOLID Principles Tutorial

## Single Responsibility Principle
Definition: A class should have only one reason to change.
Meaning: **ONE FUNCTION/CLASS = ONE RESPONSIBILITY**

**Problem**: We have a message class below that it can prepare, print and send message.

```java
public class SRPProblem {
    static class Message {
        static String messageBody;
        public static void writeMessage(String message) {
            messageBody = message;
            printMessage(messageBody);
        }

        public static void printMessage(String messageBody) {
            System.out.println("Message is : " + messageBody);
        }

        public static void replaceMessageContent(String oldWord, String newWord) {
            messageBody = messageBody.replace(oldWord, newWord);
            printMessage(messageBody);
        }

        public static void sendMessage() {
            System.out.println("Message sent. Content is : " + messageBody);
        }
    }

    public static void main(String[] args) {
        Message.writeMessage("Hello");
        Message.replaceMessageContent("Hello", "Hi");
        Message.sendMessage();
    }
}
```
**Solution** : Each of these futures should be a separate class. 
1. Write message method is one of the future that it can be seperable from others. So it should be a class and we convert it to class.

```java
public static void writeMessage(String message) {
    messageBody = message;
    PrintMessage.printMessage(messageBody);
}
```
Instead of the above, we will use below.
```java
static class WriteMessage {
    static String messageBody;
    public static void writeMessage(String message) {
        messageBody = message;
        PrintMessage.printMessage(messageBody);
    }
}
```
2. Print message method is also one of the future that it can be seperable from others. So it should be also a class and we convert it to class.

```java
public static void printMessage(String messageBody) {
    System.out.println("Message is : " + messageBody);
}
```
Instead of the above, we will use below.
```java
static class PrintMessage {
    public static void printMessage(String messageBody) {
        System.out.println("Message is : " + messageBody);
    }
}
```
3. Send message method is also one of the future that it can be seperable from others. So it should be also a class and we convert it to class.

```java
public static void sendMessage() {
    System.out.println("Message sent. Content is : " + messageBody);
}
```
Instead of the above, we will use below.
```java
static class SendMessage {
    public static void sendMessage(String messageBody) {
        System.out.println("Message sent. Content is : " + messageBody);
    }
}

```

As you will see below, we seperate futures to different classes ( write, print, send ) and each class just do it's job for appling single responsibility principle.
```java
public class SRPSolution {
    static class WriteMessage {
        static String messageBody;
        public static void writeMessage(String message) {
            messageBody = message;
            PrintMessage.printMessage(messageBody);
        }

        public static void replaceMessageContent(String oldWord, String newWord) {
            messageBody = messageBody.replace(oldWord, newWord);
            PrintMessage.printMessage(messageBody);
        }
    }

    static class PrintMessage {
        public static void printMessage(String messageBody) {
            System.out.println("Message is : " + messageBody);
        }
    }

    static class SendMessage {
        public static void sendMessage(String messageBody) {
            System.out.println("Message sent. Content is : " + messageBody);
        }
    }

    public static void main(String[] args) {
        WriteMessage.writeMessage("Hello");
        WriteMessage.replaceMessageContent("Hello", "Hi");
        SendMessage.sendMessage(WriteMessage.messageBody);
    }
}
```
Homework: Replace of message future also can be a class, you can apply single responsibility principle at this point.

## Open Close Principle
Definition: Software entities (classes, functions, etc.) should be open for extension, but closed for modification.
Meaning: **FUNCTION/CLASS = :tw-2705: EXTENSION :tw-274e: MODIFICATION**

Problem: We have a notification class below that it can notify customer as sms or mail. If we want to add a new notification type, we have to change our class.
```java
public class OCPProblem {
    enum messageType { SMS, MAIL }
    static class Notification {
        static String messageBody;
        static public void notifyCustomer(messageType type, String messageBody) {
            if (type.equals(messageType.MAIL)) {
                System.out.println("Message sent as mail. Content is : " + messageBody);
            } else if (type.equals(messageType.SMS)) {
                System.out.println("Message sent as sms. Content is : " + messageBody);
            }
        }
    }

    public static void main(String[] args) {
        Notification.notifyCustomer(messageType.MAIL, "Your application has been received.");
        Notification.notifyCustomer(messageType.SMS, "Your application has been received.");
    }
}
```
Solution : So we have to design our class that it should not change when a new notification type added.
1. Firstly, we will create an abstact class for notify.
```java
public static abstract class Notify {
		public abstract void notifyCustomer(String messageBody);
}
```
2. Now we can add sms and mail notification types that they extend our abstract class.
```java
public static class NotificationViaSMS extends Notify {
	public void notifyCustomer(String messageBody) {
		System.out.println("Message sent as sms. Content is : " + messageBody);
	}
}
public static class NotificationViaMail extends Notify {
	public void notifyCustomer(String messageBody) {
		System.out.println("Message sent as mail. Content is : " + messageBody);
	}
}
```
As a result, our code turn to below.
```java
public class OCPSolution {
    static class Notification {
        public static abstract class Notify {
            public abstract void notifyCustomer(String messageBody);
        }

        public static class NotificationViaSMS extends Notify {
            public void notifyCustomer(String messageBody) {
                System.out.println("Message sent as sms. Content is : " + messageBody);
            }
        }

        public static class NotificationViaMail extends Notify {
            public void notifyCustomer(String messageBody) {
                System.out.println("Message sent as mail. Content is : " + messageBody);
            }
        }
    }

    public static void main(String[] args) {
        Notification.NotificationViaMail mail = new Notification.NotificationViaMail();
        Notification.NotificationViaSMS sms = new Notification.NotificationViaSMS();
        mail.notifyCustomer("Your application has been received.");
        sms.notifyCustomer("Your application has been received.");
    }
}
```

After this transformation, we are free for adding new notification types without regression to anywhere.

## Liskov Substitution Principle
## Interface Segregation Principle
## Dependency Inversion Principle
