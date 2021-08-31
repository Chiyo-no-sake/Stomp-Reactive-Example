export interface ChangeEventMessage<Type> {
  subject: Type,
  changeType: "UPDATED" | "CREATED" | "DELETED"
}
