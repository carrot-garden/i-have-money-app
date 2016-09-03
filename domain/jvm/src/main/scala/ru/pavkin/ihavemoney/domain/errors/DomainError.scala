package ru.pavkin.ihavemoney.domain.errors

import java.util.UUID

import ru.pavkin.ihavemoney.domain.fortune.{AssetId, Currency, FortuneId, LiabilityId}
import ru.pavkin.ihavemoney.domain.user.UserId

sealed trait DomainError extends Throwable {
  def message: String
  override def getMessage: String = message
}
case object NegativeAmount extends DomainError {
  def message = "Adjustment can't have negative amount"
}
case object NegativeWorth extends DomainError {
  def message = "Asset can't have negative worth"
}
case class BalanceIsNotEnough(amount: BigDecimal, currency: Currency) extends DomainError {
  def message = s"Your balance ($amount ${currency.code}) is not enough for this operation"
}
case object UnsupportedCommand extends DomainError {
  def message = s"Command is not supported"
}
case object InvalidConfirmationCode extends DomainError {
  def message = s"Invalid confirmation code"
}
case object EmailIsNotYetConfirmed extends DomainError {
  def message = s"Email is not yet confirmed"
}
case object EmailAlreadyConfirmed extends DomainError {
  def message = s"Email already confirmed"
}
case class InsufficientAccessRights(user: UserId, fortune: FortuneId) extends DomainError {
  def message = s"User $user is not allowed to perform this command on fortune $fortune"
}
case class FortuneAlreadyInitialized(fortune: FortuneId) extends DomainError {
  def message = s"Fortune $fortune is already initialized, you can not perform initializer commands."
}
case class AssetNotFound(assetId: AssetId) extends DomainError {
  def message = s"Asset $assetId not found"
}
case class LiabilityNotFound(liabilityId: LiabilityId) extends DomainError {
  def message = s"Liability $liabilityId not found"
}
case class TransactionNotFound(transactionId: UUID) extends DomainError {
  def message = s"Transaction $transactionId not found"
}
