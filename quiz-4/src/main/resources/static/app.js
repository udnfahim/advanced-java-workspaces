const API = "/api/orders";

const table = document.getElementById("ordersTable");
const form = document.getElementById("orderForm");

async function loadOrders() {
    const res = await fetch(API);
    const orders = await res.json();

    table.innerHTML = "";

    let revenue = 0;
    let pending = 0;

    orders.forEach(o => {
        if (o.orderStatus === "PAID") revenue += o.totalPrice;
        if (o.orderStatus === "PENDING") pending++;

        table.innerHTML += `
            <tr>
                <td>${o.customerName}</td>
                <td>${o.productName}</td>
                <td>${o.quantity}</td>
                <td>$${o.totalPrice.toFixed(2)}</td>
                <td>${o.orderStatus}</td>
                <td>${new Date(o.localDateTime).toLocaleString()}</td>
                <td>
                    ${o.orderStatus !== "PAID"
            ? `<button onclick="markPaid('${o.id}')">Mark Paid</button>`
            : ""}
                    <button onclick="deleteOrder('${o.id}')">Delete</button>
                </td>
            </tr>
        `;
    });

    statOrders.innerText = orders.length;
    statRevenue.innerText = `$${revenue.toFixed(2)}`;
    statPending.innerText = pending;
}

form.addEventListener("submit", async e => {
    e.preventDefault();

    const order = {
        customerName: customerName.value,
        productName: productName.value,
        quantity: Number(quantity.value),
        totalPrice: Number(totalPrice.value),
        orderStatus: orderStatus.value
    };

    await fetch(API, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(order)
    });

    form.reset();
    loadOrders();
});

async function deleteOrder(id) {
    await fetch(`${API}/${id}`, { method: "DELETE" });
    loadOrders();
}

async function markPaid(id) {
    await fetch(`${API}/${id}/status?status=PAID`, {
        method: "PATCH"
    });
    loadOrders();
}

loadOrders();
