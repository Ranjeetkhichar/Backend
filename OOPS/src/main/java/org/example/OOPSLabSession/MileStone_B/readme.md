# Milestone B: Designing the Lendable System

> **The 5-Second Story:** 
> We needed a way to borrow items in our library without hardcoding book types. We built a **`Lendable` contract**, implemented shared state in an **abstract `Book` class**, and left exact rendering to concrete subclasses.

---

## 📖 The 10-Second Story Arc

```
1. THE CONTRACT (Interface)       2. THE CORE LOGIC (Abstract Class)     3. THE VARIATION (Subclasses)
┌─────────────────────────┐       ┌────────────────────────────────┐     ┌────────────────────────┐
│   Lendable Interface    │ ────► │         Abstract Book          │ ──► │  TextBook / Novel / etc│
│ "What can be borrowed?" │       │ "Shared rules & encapsulation" │     │ "Custom display details"│
└─────────────────────────┘       └────────────────────────────────┘     └────────────────────────┘
```

---

## 💡 Executive Summary (SDE2 / EM Perspective)

- **Problem:** Different physical items (Textbooks, Novels, Media) need borrow/return workflows without duplicate code.
- **Solution:** 
  1. **`Lendable` Interface:** Enforces strict contract (`lend`, `returnBook`, `isAvailable`) across all inventory items.
  2. **`Book` Abstract Class:** Encapsulates core metadata (`isbn`, `title`, `author`) and handles core lending validation.
  3. **Polymorphic Execution:** Client code depends on `Lendable`—allowing seamless addition of new item types zero breaking changes.

---

## ⚡ Key Code Blueprint

```java
// Client interacts ONLY with the abstraction
Lendable item = inventory.find("ISBN-123"); 
User member = userRegistry.get("USER-1");

if item.isAvailable() and item.lend(member) {
    // Borrowed successfully via runtime polymorphism
}
```
